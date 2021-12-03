# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server.models.asset_class import AssetClass  # noqa: F401,E501
from swagger_server.models.country import Country  # noqa: F401,E501
from swagger_server.models.license_type import LicenseType  # noqa: F401,E501
from swagger_server import util


class License(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    def __init__(self, asset_class: AssetClass=None, issuing_country: Country=None, number: str=None, license_code: str=None, valid_until: date=None):  # noqa: E501
        """License - a model defined in Swagger

        :param asset_class: The asset_class of this License.  # noqa: E501
        :type asset_class: AssetClass
        :param issuing_country: The issuing_country of this License.  # noqa: E501
        :type issuing_country: Country
        :param number: The number of this License.  # noqa: E501
        :type number: str
        :param license_code: The license_code of this License.  # noqa: E501
        :type license_code: str
        :param valid_until: The valid_until of this License.  # noqa: E501
        :type valid_until: date
        """
        self.swagger_types = {
            'asset_class': AssetClass,
            'issuing_country': Country,
            'number': str,
            'license_code': str,
            'valid_until': date
        }

        self.attribute_map = {
            'asset_class': 'assetClass',
            'issuing_country': 'issuingCountry',
            'number': 'number',
            'license_code': 'licenseCode',
            'valid_until': 'validUntil'
        }
        self._asset_class = asset_class
        self._issuing_country = issuing_country
        self._number = number
        self._license_code = license_code
        self._valid_until = valid_until

    @classmethod
    def from_dict(cls, dikt) -> 'License':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The license of this License.  # noqa: E501
        :rtype: License
        """
        return util.deserialize_model(dikt, cls)

    @property
    def asset_class(self) -> AssetClass:
        """Gets the asset_class of this License.


        :return: The asset_class of this License.
        :rtype: AssetClass
        """
        return self._asset_class

    @asset_class.setter
    def asset_class(self, asset_class: AssetClass):
        """Sets the asset_class of this License.


        :param asset_class: The asset_class of this License.
        :type asset_class: AssetClass
        """
        if asset_class is None:
            raise ValueError("Invalid value for `asset_class`, must not be `None`")  # noqa: E501

        self._asset_class = asset_class

    @property
    def issuing_country(self) -> Country:
        """Gets the issuing_country of this License.


        :return: The issuing_country of this License.
        :rtype: Country
        """
        return self._issuing_country

    @issuing_country.setter
    def issuing_country(self, issuing_country: Country):
        """Sets the issuing_country of this License.


        :param issuing_country: The issuing_country of this License.
        :type issuing_country: Country
        """

        self._issuing_country = issuing_country

    @property
    def number(self) -> str:
        """Gets the number of this License.


        :return: The number of this License.
        :rtype: str
        """
        return self._number

    @number.setter
    def number(self, number: str):
        """Sets the number of this License.


        :param number: The number of this License.
        :type number: str
        """

        self._number = number

    @property
    def license_code(self) -> str:
        """Gets the license_code of this License.

        in most countries a driver license has also a code. As TO you can exactly verify, based on this code if the license allows to operate it's assets, if the assetType too generic.  # noqa: E501

        :return: The license_code of this License.
        :rtype: str
        """
        return self._license_code

    @license_code.setter
    def license_code(self, license_code: str):
        """Sets the license_code of this License.

        in most countries a driver license has also a code. As TO you can exactly verify, based on this code if the license allows to operate it's assets, if the assetType too generic.  # noqa: E501

        :param license_code: The license_code of this License.
        :type license_code: str
        """

        self._license_code = license_code

    @property
    def valid_until(self) -> date:
        """Gets the valid_until of this License.


        :return: The valid_until of this License.
        :rtype: date
        """
        return self._valid_until

    @valid_until.setter
    def valid_until(self, valid_until: date):
        """Sets the valid_until of this License.


        :param valid_until: The valid_until of this License.
        :type valid_until: date
        """

        self._valid_until = valid_until
