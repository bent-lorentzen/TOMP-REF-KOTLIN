package org.tomp.api.operatorinformation

import io.swagger.model.AssetType
import io.swagger.model.Day
import io.swagger.model.EndpointImplementation
import io.swagger.model.Fare
import io.swagger.model.FarePart
import io.swagger.model.FarePart.UnitTypeEnum
import io.swagger.model.StationInformation
import io.swagger.model.SystemCalendar
import io.swagger.model.SystemHours
import io.swagger.model.SystemInformation
import io.swagger.model.SystemPricingPlan
import io.swagger.model.SystemRegion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.providers.assets.AssetProvider
import org.tomp.api.utils.ExternalFileService
import org.tomp.api.utils.ObjectFromFileProvider
import java.util.Arrays
import java.util.Date

@Component
@ConditionalOnProperty(value = ["tomp.providers.operatorinformation"], havingValue = "car", matchIfMissing = false)
class CarOperatorInformationProvider : OperatorInformationProvider {
    @Autowired
    var configuration: ExternalConfiguration? = null

    @Autowired
    var assetProvider: AssetProvider? = null

    @Autowired
    var fileService: ExternalFileService? = null
    override fun getAvailableAssetTypes(acceptLanguage: String?): List<AssetType?>? {
        return assetProvider.getAssetTypes()
    }

    override fun getOperatorInformation(acceptLanguage: String?): SystemInformation? {
        val info = SystemInformation()
        info.systemId = "maas-car-3342"
        info.email = "email@caroperator.org"
        info.setLanguage(Arrays.asList(acceptLanguage))
        info.name = "Car Operator"
        return info
    }

    override fun getStations(acceptLanguage: String?): List<StationInformation?>? {
        return ArrayList()
    }

    override fun getRegions(acceptLanguage: String?): MutableList<SystemRegion?>? {
        val provider = ObjectFromFileProvider<Array<SystemRegion>>()
        val regionArray = provider.getObject(
            acceptLanguage, Array<SystemRegion>::class.java,
            configuration.getRegionsFile()
        )!!
        val regions: MutableList<SystemRegion?> = ArrayList()
        for (i in regionArray.indices) {
            regions.add(regionArray[i])
        }
        return regions
    }

    override fun getPricingPlans(acceptLanguage: String?): List<SystemPricingPlan> {
        val membersOnly = SystemPricingPlan()
        membersOnly.planId = "MO"
        membersOnly.description = "Subscribed members can apply for this pricing plan"
        membersOnly.setIsTaxable(true)
        membersOnly.name = "Members only"
        var fare = Fare()
        var partsItem = FarePart()
        partsItem.amount = 1.25.toFloat()
        partsItem.currencyCode = "NL"
        partsItem.unitType = UnitTypeEnum.KM
        partsItem.type = FarePart.TypeEnum.FLEX
        fare.addPartsItem(partsItem)
        membersOnly.fare = fare
        val nonMembers = SystemPricingPlan()
        nonMembers.planId = "NM"
        nonMembers.description = "Pricing plan for non-subscribers"
        nonMembers.setIsTaxable(true)
        nonMembers.name = "Non members"
        fare = Fare()
        partsItem = FarePart()
        partsItem.amount = 1.55.toFloat()
        partsItem.currencyCode = "NL"
        partsItem.unitType = UnitTypeEnum.KM
        partsItem.type = FarePart.TypeEnum.FLEX
        fare.addPartsItem(partsItem)
        partsItem = FarePart()
        partsItem.amount = 10.toFloat()
        partsItem.currencyCode = "NL"
        partsItem.type = FarePart.TypeEnum.FIXED
        fare.addPartsItem(partsItem)
        nonMembers.fare = fare
        return Arrays.asList(membersOnly, nonMembers)
    }

    override fun getHours(acceptLanguage: String?): List<SystemHours> {
        val weekHours = SystemHours()
        weekHours.setDays(Arrays.asList(Day.MON, Day.TUE, Day.WED, Day.THU, Day.FRI))
        weekHours.startTime = "08:00"
        weekHours.endTime = "18:00"
        val hours = SystemHours()
        hours.setDays(Arrays.asList(Day.SAT))
        hours.startTime = "10:00"
        hours.endTime = "16:00"
        return Arrays.asList(weekHours, hours)
    }

    @Suppress("deprecation")
    override fun getCalendar(acceptLanguage: String?): List<SystemCalendar> {
        val c = SystemCalendar()
        c.startYear = 2019
        c.startMonth = 1
        c.startDay = 1
        c.endYear = Date().year
        c.endMonth = 12
        c.endDay = 31
        return ArrayList()
    }

    override fun getMeta(acceptLanguage: String?): List<EndpointImplementation?>? {
        return fileService.getEndPoints()
    }
}
