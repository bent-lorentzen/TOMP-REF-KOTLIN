package org.tomp.api.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.tomp.api.utils.GeoUtil;

import io.swagger.model.Asset;
import io.swagger.model.AssetClass;
import io.swagger.model.Coordinates;
import io.swagger.model.Place;
import io.swagger.model.StationInformation;
import io.swagger.model.SystemCalendar;
import io.swagger.model.SystemHours;
import io.swagger.model.SystemInformation;
import io.swagger.model.SystemRegion;
import io.swagger.model.TypeOfAsset;

@Component
@ConditionalOnProperty(value = "tomp.providers.operatorinformation", havingValue = "gbfs", matchIfMissing = false)
public class GbfsRepository implements RegionContainer, StationContainer {

	private Map<String, String> operatorInformation;
	private List<String> languages;
	private List<SystemRegion> regions = new ArrayList<>();
	private List<StationInformation> stations = new ArrayList<>();
	private List<SystemHours> systemHours = new ArrayList<>();
	private List<SystemCalendar> systemCalendar = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> bikesAtStations;
	private ArrayList<HashMap<String, Object>> freeBikes;

	public SystemInformation getOperatorInformation() {
		SystemInformation info = new SystemInformation();
		info.setEmail(operatorInformation.get("email"));
		info.setLanguage(languages);
		info.setName(operatorInformation.get("name"));
		info.setOperator(operatorInformation.get("operator"));
		info.setPhoneNumber(operatorInformation.get("phone_number"));
		info.setSystemId(operatorInformation.get("system_id"));
		return info;
	}

	public void setOperatorInformation(Map<String, String> operatorInformation) {
		this.operatorInformation = operatorInformation;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public List<SystemRegion> getRegions() {
		return regions;
	}

	public List<TypeOfAsset> getAssets() {
		List<TypeOfAsset> assets = new ArrayList<>();
		if (bikesAtStations != null) {
			for (HashMap<String, Object> e : this.bikesAtStations) {
				TypeOfAsset assetType = new TypeOfAsset();
				String stationId = e.get("station_id").toString();
				Asset assetsItem = new Asset();
				assetType.addAssetsItem(assetsItem);

				copyStationValues(stationId, assetType);

				assetType.setName("Station " + stationId);
				assetType.setAssetClass(AssetClass.BICYCLE);
				assetType.setAmountAvailable(
						BigDecimal.valueOf(Double.valueOf(e.get("num_bikes_available").toString())));

				assets.add(assetType);
			}
		}

		if (freeBikes != null) {
			TypeOfAsset assetType = new TypeOfAsset();
			assetType.setAssetClass(AssetClass.BICYCLE);

			for (HashMap<String, Object> e : freeBikes) {
				Asset asset = new Asset();
				asset.setName("Bike " + e.get("bike_id"));
				asset.setAssetId(e.get("bike_id").toString());
				asset.setPlace(toPlace(e.get("lat"), e.get("lon")));
				assetType.addAssetsItem(asset);
			}
			assets.add(assetType);
		}
		return assets;
	}

	private void copyStationValues(String stationId, TypeOfAsset assetType) {
		for (StationInformation station : getStations()) {
			if (station.getStationId().equals(stationId)) {
				Place place = new Place();
				place.setCoordinates(getCoordinates(stationId));
				place.setStationId(stationId);
				place.setName(station.getName());
				place.setPhysicalAddress(station.getPhysicalAddress());
				assetType.getAssets().get(0).setPlace(place);
			}
		}
	}

	private Coordinates getCoordinates(String stationId) {
		for (StationInformation station : getStations()) {
			if (station.getStationId().equals(stationId)) {
				return station.getCoordinates();
			}
		}
		return null;
	}

	private Place toPlace(Object lat, Object lng) {
		Place p = new Place();
		Coordinates coordinates = new Coordinates();
		coordinates.setLat(BigDecimal.valueOf((Double) lat));
		coordinates.setLng(BigDecimal.valueOf((Double) lng));
		p.setCoordinates(coordinates);
		return p;
	}

	public List<StationInformation> getStations() {
		return stations;
	}

	public List<SystemHours> getSystemHours() {
		return systemHours;
	}

	public List<SystemCalendar> getSystemCalendar() {
		return systemCalendar;
	}

	public void setBikesAtStations(ArrayList<HashMap<String, Object>> bikesAtStations) {
		this.bikesAtStations = bikesAtStations;
	}

	public void setFreeBikes(ArrayList<HashMap<String, Object>> freeBikes) {
		this.freeBikes = freeBikes;
	}

	public List<TypeOfAsset> getNearestAssets(@NotNull @Valid Place from, @Valid BigDecimal radius) {
		List<TypeOfAsset> results = new ArrayList<>();
		for (TypeOfAsset assetType : getAssets()) {
			if (assetType.getAmountAvailable() == null) {
				for (Asset asset : assetType.getAssets()) {
					if (GeoUtil.isNearby(asset.getPlace().getCoordinates(), from.getCoordinates(),
							radius.doubleValue())) {
						TypeOfAsset clone = clone(assetType);
						clone.setAssets(Arrays.asList(asset));
						results.add(clone);
					}
				}
			} else if (GeoUtil.isNearby(assetType.getAssets().get(0).getPlace().getCoordinates(), from.getCoordinates(),
					radius.doubleValue())) {
				results.add(assetType);
			}
		}
		return results;
	}

	private TypeOfAsset clone(TypeOfAsset assetType) {
		TypeOfAsset typeOfAsset = new TypeOfAsset();
		typeOfAsset.setName(assetType.getName());
		return typeOfAsset;
	}
}
