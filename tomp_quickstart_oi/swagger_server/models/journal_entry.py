# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server.models.amount_of_money import AmountOfMoney  # noqa: F401,E501
from swagger_server.models.bank_account import BankAccount  # noqa: F401,E501
from swagger_server.models.country import Country  # noqa: F401,E501
from swagger_server.models.journal_category import JournalCategory  # noqa: F401,E501
from swagger_server.models.journal_state import JournalState  # noqa: F401,E501
from swagger_server import util


class JournalEntry(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    def __init__(self, amount: float=None, amount_ex_vat: float=None, currency_code: str=None, vat_rate: float=None, vat_country_code: Country=None, category: JournalCategory=None, journal_id: str=None, journal_sequence_id: str=None, invoice_id: str=None, invoice_date: datetime=None, state: JournalState=None, expiration_date: datetime=None, comment: str=None, distance: float=None, distance_type: str=None, used_time: float=None, rental_start_mileage: float=None, bank_account: BankAccount=None, details: any=None):  # noqa: E501
        """JournalEntry - a model defined in Swagger

        :param amount: The amount of this JournalEntry.  # noqa: E501
        :type amount: float
        :param amount_ex_vat: The amount_ex_vat of this JournalEntry.  # noqa: E501
        :type amount_ex_vat: float
        :param currency_code: The currency_code of this JournalEntry.  # noqa: E501
        :type currency_code: str
        :param vat_rate: The vat_rate of this JournalEntry.  # noqa: E501
        :type vat_rate: float
        :param vat_country_code: The vat_country_code of this JournalEntry.  # noqa: E501
        :type vat_country_code: Country
        :param category: The category of this JournalEntry.  # noqa: E501
        :type category: JournalCategory
        :param journal_id: The journal_id of this JournalEntry.  # noqa: E501
        :type journal_id: str
        :param journal_sequence_id: The journal_sequence_id of this JournalEntry.  # noqa: E501
        :type journal_sequence_id: str
        :param invoice_id: The invoice_id of this JournalEntry.  # noqa: E501
        :type invoice_id: str
        :param invoice_date: The invoice_date of this JournalEntry.  # noqa: E501
        :type invoice_date: datetime
        :param state: The state of this JournalEntry.  # noqa: E501
        :type state: JournalState
        :param expiration_date: The expiration_date of this JournalEntry.  # noqa: E501
        :type expiration_date: datetime
        :param comment: The comment of this JournalEntry.  # noqa: E501
        :type comment: str
        :param distance: The distance of this JournalEntry.  # noqa: E501
        :type distance: float
        :param distance_type: The distance_type of this JournalEntry.  # noqa: E501
        :type distance_type: str
        :param used_time: The used_time of this JournalEntry.  # noqa: E501
        :type used_time: float
        :param rental_start_mileage: The rental_start_mileage of this JournalEntry.  # noqa: E501
        :type rental_start_mileage: float
        :param bank_account: The bank_account of this JournalEntry.  # noqa: E501
        :type bank_account: BankAccount
        :param details: The details of this JournalEntry.  # noqa: E501
        :type details: any
        """
        self.swagger_types = {
            'amount': float,
            'amount_ex_vat': float,
            'currency_code': str,
            'vat_rate': float,
            'vat_country_code': Country,
            'category': JournalCategory,
            'journal_id': str,
            'journal_sequence_id': str,
            'invoice_id': str,
            'invoice_date': datetime,
            'state': JournalState,
            'expiration_date': datetime,
            'comment': str,
            'distance': float,
            'distance_type': str,
            'used_time': float,
            'rental_start_mileage': float,
            'bank_account': BankAccount,
            'details': any
        }

        self.attribute_map = {
            'amount': 'amount',
            'amount_ex_vat': 'amountExVat',
            'currency_code': 'currencyCode',
            'vat_rate': 'vatRate',
            'vat_country_code': 'vatCountryCode',
            'category': 'category',
            'journal_id': 'journalId',
            'journal_sequence_id': 'journalSequenceId',
            'invoice_id': 'invoiceId',
            'invoice_date': 'invoiceDate',
            'state': 'state',
            'expiration_date': 'expirationDate',
            'comment': 'comment',
            'distance': 'distance',
            'distance_type': 'distanceType',
            'used_time': 'usedTime',
            'rental_start_mileage': 'rentalStartMileage',
            'bank_account': 'bankAccount',
            'details': 'details'
        }
        self._amount = amount
        self._amount_ex_vat = amount_ex_vat
        self._currency_code = currency_code
        self._vat_rate = vat_rate
        self._vat_country_code = vat_country_code
        self._category = category
        self._journal_id = journal_id
        self._journal_sequence_id = journal_sequence_id
        self._invoice_id = invoice_id
        self._invoice_date = invoice_date
        self._state = state
        self._expiration_date = expiration_date
        self._comment = comment
        self._distance = distance
        self._distance_type = distance_type
        self._used_time = used_time
        self._rental_start_mileage = rental_start_mileage
        self._bank_account = bank_account
        self._details = details

    @classmethod
    def from_dict(cls, dikt) -> 'JournalEntry':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The journalEntry of this JournalEntry.  # noqa: E501
        :rtype: JournalEntry
        """
        return util.deserialize_model(dikt, cls)

    @property
    def amount(self) -> float:
        """Gets the amount of this JournalEntry.

        This should be in the base unit as defined by the ISO 4217 currency code with the appropriate number of decimal places and omitting the currency symbol. e.g. if the price is in US Dollars the price would be 9.95. This is inclusive VAT  # noqa: E501

        :return: The amount of this JournalEntry.
        :rtype: float
        """
        return self._amount

    @amount.setter
    def amount(self, amount: float):
        """Sets the amount of this JournalEntry.

        This should be in the base unit as defined by the ISO 4217 currency code with the appropriate number of decimal places and omitting the currency symbol. e.g. if the price is in US Dollars the price would be 9.95. This is inclusive VAT  # noqa: E501

        :param amount: The amount of this JournalEntry.
        :type amount: float
        """

        self._amount = amount

    @property
    def amount_ex_vat(self) -> float:
        """Gets the amount_ex_vat of this JournalEntry.


        :return: The amount_ex_vat of this JournalEntry.
        :rtype: float
        """
        return self._amount_ex_vat

    @amount_ex_vat.setter
    def amount_ex_vat(self, amount_ex_vat: float):
        """Sets the amount_ex_vat of this JournalEntry.


        :param amount_ex_vat: The amount_ex_vat of this JournalEntry.
        :type amount_ex_vat: float
        """

        self._amount_ex_vat = amount_ex_vat

    @property
    def currency_code(self) -> str:
        """Gets the currency_code of this JournalEntry.

        ISO 4217 currency code  # noqa: E501

        :return: The currency_code of this JournalEntry.
        :rtype: str
        """
        return self._currency_code

    @currency_code.setter
    def currency_code(self, currency_code: str):
        """Sets the currency_code of this JournalEntry.

        ISO 4217 currency code  # noqa: E501

        :param currency_code: The currency_code of this JournalEntry.
        :type currency_code: str
        """

        self._currency_code = currency_code

    @property
    def vat_rate(self) -> float:
        """Gets the vat_rate of this JournalEntry.

        value added tax rate (percentage of amount)  # noqa: E501

        :return: The vat_rate of this JournalEntry.
        :rtype: float
        """
        return self._vat_rate

    @vat_rate.setter
    def vat_rate(self, vat_rate: float):
        """Sets the vat_rate of this JournalEntry.

        value added tax rate (percentage of amount)  # noqa: E501

        :param vat_rate: The vat_rate of this JournalEntry.
        :type vat_rate: float
        """

        self._vat_rate = vat_rate

    @property
    def vat_country_code(self) -> Country:
        """Gets the vat_country_code of this JournalEntry.


        :return: The vat_country_code of this JournalEntry.
        :rtype: Country
        """
        return self._vat_country_code

    @vat_country_code.setter
    def vat_country_code(self, vat_country_code: Country):
        """Sets the vat_country_code of this JournalEntry.


        :param vat_country_code: The vat_country_code of this JournalEntry.
        :type vat_country_code: Country
        """

        self._vat_country_code = vat_country_code

    @property
    def category(self) -> JournalCategory:
        """Gets the category of this JournalEntry.


        :return: The category of this JournalEntry.
        :rtype: JournalCategory
        """
        return self._category

    @category.setter
    def category(self, category: JournalCategory):
        """Sets the category of this JournalEntry.


        :param category: The category of this JournalEntry.
        :type category: JournalCategory
        """

        self._category = category

    @property
    def journal_id(self) -> str:
        """Gets the journal_id of this JournalEntry.

        id of the entry, leg id can be reused  # noqa: E501

        :return: The journal_id of this JournalEntry.
        :rtype: str
        """
        return self._journal_id

    @journal_id.setter
    def journal_id(self, journal_id: str):
        """Sets the journal_id of this JournalEntry.

        id of the entry, leg id can be reused  # noqa: E501

        :param journal_id: The journal_id of this JournalEntry.
        :type journal_id: str
        """

        self._journal_id = journal_id

    @property
    def journal_sequence_id(self) -> str:
        """Gets the journal_sequence_id of this JournalEntry.

        sequence id of the entry, in combination with journalId unique from TO perspective.  # noqa: E501

        :return: The journal_sequence_id of this JournalEntry.
        :rtype: str
        """
        return self._journal_sequence_id

    @journal_sequence_id.setter
    def journal_sequence_id(self, journal_sequence_id: str):
        """Sets the journal_sequence_id of this JournalEntry.

        sequence id of the entry, in combination with journalId unique from TO perspective.  # noqa: E501

        :param journal_sequence_id: The journal_sequence_id of this JournalEntry.
        :type journal_sequence_id: str
        """

        self._journal_sequence_id = journal_sequence_id

    @property
    def invoice_id(self) -> str:
        """Gets the invoice_id of this JournalEntry.

        the number of the invoice. Should be filled in when invoiced.  # noqa: E501

        :return: The invoice_id of this JournalEntry.
        :rtype: str
        """
        return self._invoice_id

    @invoice_id.setter
    def invoice_id(self, invoice_id: str):
        """Sets the invoice_id of this JournalEntry.

        the number of the invoice. Should be filled in when invoiced.  # noqa: E501

        :param invoice_id: The invoice_id of this JournalEntry.
        :type invoice_id: str
        """

        self._invoice_id = invoice_id

    @property
    def invoice_date(self) -> datetime:
        """Gets the invoice_date of this JournalEntry.


        :return: The invoice_date of this JournalEntry.
        :rtype: datetime
        """
        return self._invoice_date

    @invoice_date.setter
    def invoice_date(self, invoice_date: datetime):
        """Sets the invoice_date of this JournalEntry.


        :param invoice_date: The invoice_date of this JournalEntry.
        :type invoice_date: datetime
        """

        self._invoice_date = invoice_date

    @property
    def state(self) -> JournalState:
        """Gets the state of this JournalEntry.


        :return: The state of this JournalEntry.
        :rtype: JournalState
        """
        return self._state

    @state.setter
    def state(self, state: JournalState):
        """Sets the state of this JournalEntry.


        :param state: The state of this JournalEntry.
        :type state: JournalState
        """

        self._state = state

    @property
    def expiration_date(self) -> datetime:
        """Gets the expiration_date of this JournalEntry.


        :return: The expiration_date of this JournalEntry.
        :rtype: datetime
        """
        return self._expiration_date

    @expiration_date.setter
    def expiration_date(self, expiration_date: datetime):
        """Sets the expiration_date of this JournalEntry.


        :param expiration_date: The expiration_date of this JournalEntry.
        :type expiration_date: datetime
        """

        self._expiration_date = expiration_date

    @property
    def comment(self) -> str:
        """Gets the comment of this JournalEntry.


        :return: The comment of this JournalEntry.
        :rtype: str
        """
        return self._comment

    @comment.setter
    def comment(self, comment: str):
        """Sets the comment of this JournalEntry.


        :param comment: The comment of this JournalEntry.
        :type comment: str
        """

        self._comment = comment

    @property
    def distance(self) -> float:
        """Gets the distance of this JournalEntry.

        the travelled distance. Only if applicable.  # noqa: E501

        :return: The distance of this JournalEntry.
        :rtype: float
        """
        return self._distance

    @distance.setter
    def distance(self, distance: float):
        """Sets the distance of this JournalEntry.

        the travelled distance. Only if applicable.  # noqa: E501

        :param distance: The distance of this JournalEntry.
        :type distance: float
        """

        self._distance = distance

    @property
    def distance_type(self) -> str:
        """Gets the distance_type of this JournalEntry.


        :return: The distance_type of this JournalEntry.
        :rtype: str
        """
        return self._distance_type

    @distance_type.setter
    def distance_type(self, distance_type: str):
        """Sets the distance_type of this JournalEntry.


        :param distance_type: The distance_type of this JournalEntry.
        :type distance_type: str
        """
        allowed_values = ["KM", "MILE"]  # noqa: E501
        if distance_type not in allowed_values:
            raise ValueError(
                "Invalid value for `distance_type` ({0}), must be one of {1}"
                .format(distance_type, allowed_values)
            )

        self._distance_type = distance_type

    @property
    def used_time(self) -> float:
        """Gets the used_time of this JournalEntry.

        the time in seconds that the assed is used. Only if applicable.  # noqa: E501

        :return: The used_time of this JournalEntry.
        :rtype: float
        """
        return self._used_time

    @used_time.setter
    def used_time(self, used_time: float):
        """Sets the used_time of this JournalEntry.

        the time in seconds that the assed is used. Only if applicable.  # noqa: E501

        :param used_time: The used_time of this JournalEntry.
        :type used_time: float
        """

        self._used_time = used_time

    @property
    def rental_start_mileage(self) -> float:
        """Gets the rental_start_mileage of this JournalEntry.

        the mileage at the start of the rental. 'DistanceType' field is also applicable here  # noqa: E501

        :return: The rental_start_mileage of this JournalEntry.
        :rtype: float
        """
        return self._rental_start_mileage

    @rental_start_mileage.setter
    def rental_start_mileage(self, rental_start_mileage: float):
        """Sets the rental_start_mileage of this JournalEntry.

        the mileage at the start of the rental. 'DistanceType' field is also applicable here  # noqa: E501

        :param rental_start_mileage: The rental_start_mileage of this JournalEntry.
        :type rental_start_mileage: float
        """

        self._rental_start_mileage = rental_start_mileage

    @property
    def bank_account(self) -> BankAccount:
        """Gets the bank_account of this JournalEntry.


        :return: The bank_account of this JournalEntry.
        :rtype: BankAccount
        """
        return self._bank_account

    @bank_account.setter
    def bank_account(self, bank_account: BankAccount):
        """Sets the bank_account of this JournalEntry.


        :param bank_account: The bank_account of this JournalEntry.
        :type bank_account: BankAccount
        """

        self._bank_account = bank_account

    @property
    def details(self) -> any:
        """Gets the details of this JournalEntry.

        the specification of the amount; how is it composed.  # noqa: E501

        :return: The details of this JournalEntry.
        :rtype: any
        """
        return self._details

    @details.setter
    def details(self, details: any):
        """Sets the details of this JournalEntry.

        the specification of the amount; how is it composed.  # noqa: E501

        :param details: The details of this JournalEntry.
        :type details: any
        """

        self._details = details
