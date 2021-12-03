# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server.models.asset_properties import AssetProperties  # noqa: F401,E501
from swagger_server import util


class Asset(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    def __init__(self, id: str=None, is_reserved: bool=None, is_reserved_from: datetime=None, is_reserved_to: datetime=None, is_disabled: bool=None, rental_url: str=None, rental_url_android: str=None, rental_url_ios: str=None, mileage: float=None, license_plate: str=None, overridden_properties: AssetProperties=None):  # noqa: E501
        """Asset - a model defined in Swagger

        :param id: The id of this Asset.  # noqa: E501
        :type id: str
        :param is_reserved: The is_reserved of this Asset.  # noqa: E501
        :type is_reserved: bool
        :param is_reserved_from: The is_reserved_from of this Asset.  # noqa: E501
        :type is_reserved_from: datetime
        :param is_reserved_to: The is_reserved_to of this Asset.  # noqa: E501
        :type is_reserved_to: datetime
        :param is_disabled: The is_disabled of this Asset.  # noqa: E501
        :type is_disabled: bool
        :param rental_url: The rental_url of this Asset.  # noqa: E501
        :type rental_url: str
        :param rental_url_android: The rental_url_android of this Asset.  # noqa: E501
        :type rental_url_android: str
        :param rental_url_ios: The rental_url_ios of this Asset.  # noqa: E501
        :type rental_url_ios: str
        :param mileage: The mileage of this Asset.  # noqa: E501
        :type mileage: float
        :param license_plate: The license_plate of this Asset.  # noqa: E501
        :type license_plate: str
        :param overridden_properties: The overridden_properties of this Asset.  # noqa: E501
        :type overridden_properties: AssetProperties
        """
        self.swagger_types = {
            'id': str,
            'is_reserved': bool,
            'is_reserved_from': datetime,
            'is_reserved_to': datetime,
            'is_disabled': bool,
            'rental_url': str,
            'rental_url_android': str,
            'rental_url_ios': str,
            'mileage': float,
            'license_plate': str,
            'overridden_properties': AssetProperties
        }

        self.attribute_map = {
            'id': 'id',
            'is_reserved': 'isReserved',
            'is_reserved_from': 'isReservedFrom',
            'is_reserved_to': 'isReservedTo',
            'is_disabled': 'isDisabled',
            'rental_url': 'rentalUrl',
            'rental_url_android': 'rentalUrlAndroid',
            'rental_url_ios': 'rentalUrlIOS',
            'mileage': 'mileage',
            'license_plate': 'licensePlate',
            'overridden_properties': 'overriddenProperties'
        }
        self._id = id
        self._is_reserved = is_reserved
        self._is_reserved_from = is_reserved_from
        self._is_reserved_to = is_reserved_to
        self._is_disabled = is_disabled
        self._rental_url = rental_url
        self._rental_url_android = rental_url_android
        self._rental_url_ios = rental_url_ios
        self._mileage = mileage
        self._license_plate = license_plate
        self._overridden_properties = overridden_properties

    @classmethod
    def from_dict(cls, dikt) -> 'Asset':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The asset of this Asset.  # noqa: E501
        :rtype: Asset
        """
        return util.deserialize_model(dikt, cls)

    @property
    def id(self) -> str:
        """Gets the id of this Asset.

        Identifier of an asset. Whenever used in Operator Information changed after every trip (GDPR).  # noqa: E501

        :return: The id of this Asset.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this Asset.

        Identifier of an asset. Whenever used in Operator Information changed after every trip (GDPR).  # noqa: E501

        :param id: The id of this Asset.
        :type id: str
        """
        if id is None:
            raise ValueError("Invalid value for `id`, must not be `None`")  # noqa: E501

        self._id = id

    @property
    def is_reserved(self) -> bool:
        """Gets the is_reserved of this Asset.

        true indicates the bike is currently reserved for someone else  # noqa: E501

        :return: The is_reserved of this Asset.
        :rtype: bool
        """
        return self._is_reserved

    @is_reserved.setter
    def is_reserved(self, is_reserved: bool):
        """Sets the is_reserved of this Asset.

        true indicates the bike is currently reserved for someone else  # noqa: E501

        :param is_reserved: The is_reserved of this Asset.
        :type is_reserved: bool
        """

        self._is_reserved = is_reserved

    @property
    def is_reserved_from(self) -> datetime:
        """Gets the is_reserved_from of this Asset.

        optional addition to determine if an asset is reserved in the future  # noqa: E501

        :return: The is_reserved_from of this Asset.
        :rtype: datetime
        """
        return self._is_reserved_from

    @is_reserved_from.setter
    def is_reserved_from(self, is_reserved_from: datetime):
        """Sets the is_reserved_from of this Asset.

        optional addition to determine if an asset is reserved in the future  # noqa: E501

        :param is_reserved_from: The is_reserved_from of this Asset.
        :type is_reserved_from: datetime
        """

        self._is_reserved_from = is_reserved_from

    @property
    def is_reserved_to(self) -> datetime:
        """Gets the is_reserved_to of this Asset.

        optional addition to determine when asset is available in the future  # noqa: E501

        :return: The is_reserved_to of this Asset.
        :rtype: datetime
        """
        return self._is_reserved_to

    @is_reserved_to.setter
    def is_reserved_to(self, is_reserved_to: datetime):
        """Sets the is_reserved_to of this Asset.

        optional addition to determine when asset is available in the future  # noqa: E501

        :param is_reserved_to: The is_reserved_to of this Asset.
        :type is_reserved_to: datetime
        """

        self._is_reserved_to = is_reserved_to

    @property
    def is_disabled(self) -> bool:
        """Gets the is_disabled of this Asset.

        true indicates the asset is currently disabled (broken)  # noqa: E501

        :return: The is_disabled of this Asset.
        :rtype: bool
        """
        return self._is_disabled

    @is_disabled.setter
    def is_disabled(self, is_disabled: bool):
        """Sets the is_disabled of this Asset.

        true indicates the asset is currently disabled (broken)  # noqa: E501

        :param is_disabled: The is_disabled of this Asset.
        :type is_disabled: bool
        """

        self._is_disabled = is_disabled

    @property
    def rental_url(self) -> str:
        """Gets the rental_url of this Asset.

        deep-linking option from GBFS+. Only added to be consistent with GBFS 2.0  # noqa: E501

        :return: The rental_url of this Asset.
        :rtype: str
        """
        return self._rental_url

    @rental_url.setter
    def rental_url(self, rental_url: str):
        """Sets the rental_url of this Asset.

        deep-linking option from GBFS+. Only added to be consistent with GBFS 2.0  # noqa: E501

        :param rental_url: The rental_url of this Asset.
        :type rental_url: str
        """

        self._rental_url = rental_url

    @property
    def rental_url_android(self) -> str:
        """Gets the rental_url_android of this Asset.

        deep-linking option from GBFS 2.0. Only added to be consistent with GBFS 2.0  # noqa: E501

        :return: The rental_url_android of this Asset.
        :rtype: str
        """
        return self._rental_url_android

    @rental_url_android.setter
    def rental_url_android(self, rental_url_android: str):
        """Sets the rental_url_android of this Asset.

        deep-linking option from GBFS 2.0. Only added to be consistent with GBFS 2.0  # noqa: E501

        :param rental_url_android: The rental_url_android of this Asset.
        :type rental_url_android: str
        """

        self._rental_url_android = rental_url_android

    @property
    def rental_url_ios(self) -> str:
        """Gets the rental_url_ios of this Asset.

        deep-linking option from GBFS 2.0. Only added to be consistent with GBFS 2.0  # noqa: E501

        :return: The rental_url_ios of this Asset.
        :rtype: str
        """
        return self._rental_url_ios

    @rental_url_ios.setter
    def rental_url_ios(self, rental_url_ios: str):
        """Sets the rental_url_ios of this Asset.

        deep-linking option from GBFS 2.0. Only added to be consistent with GBFS 2.0  # noqa: E501

        :param rental_url_ios: The rental_url_ios of this Asset.
        :type rental_url_ios: str
        """

        self._rental_url_ios = rental_url_ios

    @property
    def mileage(self) -> float:
        """Gets the mileage of this Asset.

        the current mileage of the asset  # noqa: E501

        :return: The mileage of this Asset.
        :rtype: float
        """
        return self._mileage

    @mileage.setter
    def mileage(self, mileage: float):
        """Sets the mileage of this Asset.

        the current mileage of the asset  # noqa: E501

        :param mileage: The mileage of this Asset.
        :type mileage: float
        """

        self._mileage = mileage

    @property
    def license_plate(self) -> str:
        """Gets the license_plate of this Asset.

        the usage of this field requires a secure environment. When assets are published in available-assets, this field can be used to track assets. Be aware of this.  # noqa: E501

        :return: The license_plate of this Asset.
        :rtype: str
        """
        return self._license_plate

    @license_plate.setter
    def license_plate(self, license_plate: str):
        """Sets the license_plate of this Asset.

        the usage of this field requires a secure environment. When assets are published in available-assets, this field can be used to track assets. Be aware of this.  # noqa: E501

        :param license_plate: The license_plate of this Asset.
        :type license_plate: str
        """

        self._license_plate = license_plate

    @property
    def overridden_properties(self) -> AssetProperties:
        """Gets the overridden_properties of this Asset.


        :return: The overridden_properties of this Asset.
        :rtype: AssetProperties
        """
        return self._overridden_properties

    @overridden_properties.setter
    def overridden_properties(self, overridden_properties: AssetProperties):
        """Sets the overridden_properties of this Asset.


        :param overridden_properties: The overridden_properties of this Asset.
        :type overridden_properties: AssetProperties
        """
        if overridden_properties is None:
            raise ValueError("Invalid value for `overridden_properties`, must not be `None`")  # noqa: E501

        self._overridden_properties = overridden_properties
