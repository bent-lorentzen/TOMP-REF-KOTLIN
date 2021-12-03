# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server.models.amount_of_money import AmountOfMoney  # noqa: F401,E501
from swagger_server.models.condition import Condition  # noqa: F401,E501
from swagger_server.models.country import Country  # noqa: F401,E501
from swagger_server import util


class ConditionDeposit(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    def __init__(self, amount: float=None, amount_ex_vat: float=None, currency_code: str=None, vat_rate: float=None, vat_country_code: Country=None, condition_type: str=None, id: str=None):  # noqa: E501
        """ConditionDeposit - a model defined in Swagger

        :param amount: The amount of this ConditionDeposit.  # noqa: E501
        :type amount: float
        :param amount_ex_vat: The amount_ex_vat of this ConditionDeposit.  # noqa: E501
        :type amount_ex_vat: float
        :param currency_code: The currency_code of this ConditionDeposit.  # noqa: E501
        :type currency_code: str
        :param vat_rate: The vat_rate of this ConditionDeposit.  # noqa: E501
        :type vat_rate: float
        :param vat_country_code: The vat_country_code of this ConditionDeposit.  # noqa: E501
        :type vat_country_code: Country
        :param condition_type: The condition_type of this ConditionDeposit.  # noqa: E501
        :type condition_type: str
        :param id: The id of this ConditionDeposit.  # noqa: E501
        :type id: str
        """
        self.swagger_types = {
            'amount': float,
            'amount_ex_vat': float,
            'currency_code': str,
            'vat_rate': float,
            'vat_country_code': Country,
            'condition_type': str,
            'id': str
        }

        self.attribute_map = {
            'amount': 'amount',
            'amount_ex_vat': 'amountExVat',
            'currency_code': 'currencyCode',
            'vat_rate': 'vatRate',
            'vat_country_code': 'vatCountryCode',
            'condition_type': 'conditionType',
            'id': 'id'
        }
        self._amount = amount
        self._amount_ex_vat = amount_ex_vat
        self._currency_code = currency_code
        self._vat_rate = vat_rate
        self._vat_country_code = vat_country_code
        self._condition_type = condition_type
        self._id = id

    @classmethod
    def from_dict(cls, dikt) -> 'ConditionDeposit':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The conditionDeposit of this ConditionDeposit.  # noqa: E501
        :rtype: ConditionDeposit
        """
        return util.deserialize_model(dikt, cls)

    @property
    def amount(self) -> float:
        """Gets the amount of this ConditionDeposit.

        This should be in the base unit as defined by the ISO 4217 currency code with the appropriate number of decimal places and omitting the currency symbol. e.g. if the price is in US Dollars the price would be 9.95. This is inclusive VAT  # noqa: E501

        :return: The amount of this ConditionDeposit.
        :rtype: float
        """
        return self._amount

    @amount.setter
    def amount(self, amount: float):
        """Sets the amount of this ConditionDeposit.

        This should be in the base unit as defined by the ISO 4217 currency code with the appropriate number of decimal places and omitting the currency symbol. e.g. if the price is in US Dollars the price would be 9.95. This is inclusive VAT  # noqa: E501

        :param amount: The amount of this ConditionDeposit.
        :type amount: float
        """

        self._amount = amount

    @property
    def amount_ex_vat(self) -> float:
        """Gets the amount_ex_vat of this ConditionDeposit.


        :return: The amount_ex_vat of this ConditionDeposit.
        :rtype: float
        """
        return self._amount_ex_vat

    @amount_ex_vat.setter
    def amount_ex_vat(self, amount_ex_vat: float):
        """Sets the amount_ex_vat of this ConditionDeposit.


        :param amount_ex_vat: The amount_ex_vat of this ConditionDeposit.
        :type amount_ex_vat: float
        """

        self._amount_ex_vat = amount_ex_vat

    @property
    def currency_code(self) -> str:
        """Gets the currency_code of this ConditionDeposit.

        ISO 4217 currency code  # noqa: E501

        :return: The currency_code of this ConditionDeposit.
        :rtype: str
        """
        return self._currency_code

    @currency_code.setter
    def currency_code(self, currency_code: str):
        """Sets the currency_code of this ConditionDeposit.

        ISO 4217 currency code  # noqa: E501

        :param currency_code: The currency_code of this ConditionDeposit.
        :type currency_code: str
        """

        self._currency_code = currency_code

    @property
    def vat_rate(self) -> float:
        """Gets the vat_rate of this ConditionDeposit.

        value added tax rate (percentage of amount)  # noqa: E501

        :return: The vat_rate of this ConditionDeposit.
        :rtype: float
        """
        return self._vat_rate

    @vat_rate.setter
    def vat_rate(self, vat_rate: float):
        """Sets the vat_rate of this ConditionDeposit.

        value added tax rate (percentage of amount)  # noqa: E501

        :param vat_rate: The vat_rate of this ConditionDeposit.
        :type vat_rate: float
        """

        self._vat_rate = vat_rate

    @property
    def vat_country_code(self) -> Country:
        """Gets the vat_country_code of this ConditionDeposit.


        :return: The vat_country_code of this ConditionDeposit.
        :rtype: Country
        """
        return self._vat_country_code

    @vat_country_code.setter
    def vat_country_code(self, vat_country_code: Country):
        """Sets the vat_country_code of this ConditionDeposit.


        :param vat_country_code: The vat_country_code of this ConditionDeposit.
        :type vat_country_code: Country
        """

        self._vat_country_code = vat_country_code

    @property
    def condition_type(self) -> str:
        """Gets the condition_type of this ConditionDeposit.

        The specific subclass of condition, should match the schema name exactly  # noqa: E501

        :return: The condition_type of this ConditionDeposit.
        :rtype: str
        """
        return self._condition_type

    @condition_type.setter
    def condition_type(self, condition_type: str):
        """Sets the condition_type of this ConditionDeposit.

        The specific subclass of condition, should match the schema name exactly  # noqa: E501

        :param condition_type: The condition_type of this ConditionDeposit.
        :type condition_type: str
        """
        if condition_type is None:
            raise ValueError("Invalid value for `condition_type`, must not be `None`")  # noqa: E501

        self._condition_type = condition_type

    @property
    def id(self) -> str:
        """Gets the id of this ConditionDeposit.

        An identifier for this condition that can be used to refer to this condition  # noqa: E501

        :return: The id of this ConditionDeposit.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this ConditionDeposit.

        An identifier for this condition that can be used to refer to this condition  # noqa: E501

        :param id: The id of this ConditionDeposit.
        :type id: str
        """

        self._id = id
