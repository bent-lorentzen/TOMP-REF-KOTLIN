# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server.models.card_type import CardType  # noqa: F401,E501
from swagger_server.models.license_type import LicenseType  # noqa: F401,E501
from swagger_server.models.requirements import Requirements  # noqa: F401,E501
from swagger_server import util


class Traveler(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    def __init__(self, is_validated: bool=None, age: int=None, reference_number: str=None, card_types: List[CardType]=None, license_types: List[LicenseType]=None, requirements: Requirements=None, known_identifier: str=None, known_identifier_provider: str=None):  # noqa: E501
        """Traveler - a model defined in Swagger

        :param is_validated: The is_validated of this Traveler.  # noqa: E501
        :type is_validated: bool
        :param age: The age of this Traveler.  # noqa: E501
        :type age: int
        :param reference_number: The reference_number of this Traveler.  # noqa: E501
        :type reference_number: str
        :param card_types: The card_types of this Traveler.  # noqa: E501
        :type card_types: List[CardType]
        :param license_types: The license_types of this Traveler.  # noqa: E501
        :type license_types: List[LicenseType]
        :param requirements: The requirements of this Traveler.  # noqa: E501
        :type requirements: Requirements
        :param known_identifier: The known_identifier of this Traveler.  # noqa: E501
        :type known_identifier: str
        :param known_identifier_provider: The known_identifier_provider of this Traveler.  # noqa: E501
        :type known_identifier_provider: str
        """
        self.swagger_types = {
            'is_validated': bool,
            'age': int,
            'reference_number': str,
            'card_types': List[CardType],
            'license_types': List[LicenseType],
            'requirements': Requirements,
            'known_identifier': str,
            'known_identifier_provider': str
        }

        self.attribute_map = {
            'is_validated': 'isValidated',
            'age': 'age',
            'reference_number': 'referenceNumber',
            'card_types': 'cardTypes',
            'license_types': 'licenseTypes',
            'requirements': 'requirements',
            'known_identifier': 'knownIdentifier',
            'known_identifier_provider': 'knownIdentifierProvider'
        }
        self._is_validated = is_validated
        self._age = age
        self._reference_number = reference_number
        self._card_types = card_types
        self._license_types = license_types
        self._requirements = requirements
        self._known_identifier = known_identifier
        self._known_identifier_provider = known_identifier_provider

    @classmethod
    def from_dict(cls, dikt) -> 'Traveler':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The traveler of this Traveler.  # noqa: E501
        :rtype: Traveler
        """
        return util.deserialize_model(dikt, cls)

    @property
    def is_validated(self) -> bool:
        """Gets the is_validated of this Traveler.

        Whether this traveler's identity and properties have been verified by the MaaS provider  # noqa: E501

        :return: The is_validated of this Traveler.
        :rtype: bool
        """
        return self._is_validated

    @is_validated.setter
    def is_validated(self, is_validated: bool):
        """Sets the is_validated of this Traveler.

        Whether this traveler's identity and properties have been verified by the MaaS provider  # noqa: E501

        :param is_validated: The is_validated of this Traveler.
        :type is_validated: bool
        """

        self._is_validated = is_validated

    @property
    def age(self) -> int:
        """Gets the age of this Traveler.

        Age of the traveler, may be approximate  # noqa: E501

        :return: The age of this Traveler.
        :rtype: int
        """
        return self._age

    @age.setter
    def age(self, age: int):
        """Sets the age of this Traveler.

        Age of the traveler, may be approximate  # noqa: E501

        :param age: The age of this Traveler.
        :type age: int
        """

        self._age = age

    @property
    def reference_number(self) -> str:
        """Gets the reference_number of this Traveler.

        reference number of the traveler. This number could be used to refer to in the planning result.  # noqa: E501

        :return: The reference_number of this Traveler.
        :rtype: str
        """
        return self._reference_number

    @reference_number.setter
    def reference_number(self, reference_number: str):
        """Sets the reference_number of this Traveler.

        reference number of the traveler. This number could be used to refer to in the planning result.  # noqa: E501

        :param reference_number: The reference_number of this Traveler.
        :type reference_number: str
        """

        self._reference_number = reference_number

    @property
    def card_types(self) -> List[CardType]:
        """Gets the card_types of this Traveler.

        The kind of cards this traveler possesses  # noqa: E501

        :return: The card_types of this Traveler.
        :rtype: List[CardType]
        """
        return self._card_types

    @card_types.setter
    def card_types(self, card_types: List[CardType]):
        """Sets the card_types of this Traveler.

        The kind of cards this traveler possesses  # noqa: E501

        :param card_types: The card_types of this Traveler.
        :type card_types: List[CardType]
        """

        self._card_types = card_types

    @property
    def license_types(self) -> List[LicenseType]:
        """Gets the license_types of this Traveler.

        The kind of licenses this traveler possesses  # noqa: E501

        :return: The license_types of this Traveler.
        :rtype: List[LicenseType]
        """
        return self._license_types

    @license_types.setter
    def license_types(self, license_types: List[LicenseType]):
        """Sets the license_types of this Traveler.

        The kind of licenses this traveler possesses  # noqa: E501

        :param license_types: The license_types of this Traveler.
        :type license_types: List[LicenseType]
        """

        self._license_types = license_types

    @property
    def requirements(self) -> Requirements:
        """Gets the requirements of this Traveler.


        :return: The requirements of this Traveler.
        :rtype: Requirements
        """
        return self._requirements

    @requirements.setter
    def requirements(self, requirements: Requirements):
        """Sets the requirements of this Traveler.


        :param requirements: The requirements of this Traveler.
        :type requirements: Requirements
        """

        self._requirements = requirements

    @property
    def known_identifier(self) -> str:
        """Gets the known_identifier of this Traveler.

        identifier for this traveler in the personal data store. This identifier can be used to get personal information from the provider specified in the \"knownIdentifierProvider\"  # noqa: E501

        :return: The known_identifier of this Traveler.
        :rtype: str
        """
        return self._known_identifier

    @known_identifier.setter
    def known_identifier(self, known_identifier: str):
        """Sets the known_identifier of this Traveler.

        identifier for this traveler in the personal data store. This identifier can be used to get personal information from the provider specified in the \"knownIdentifierProvider\"  # noqa: E501

        :param known_identifier: The known_identifier of this Traveler.
        :type known_identifier: str
        """

        self._known_identifier = known_identifier

    @property
    def known_identifier_provider(self) -> str:
        """Gets the known_identifier_provider of this Traveler.

        provider for personal information. Can be a URI or identifier.  # noqa: E501

        :return: The known_identifier_provider of this Traveler.
        :rtype: str
        """
        return self._known_identifier_provider

    @known_identifier_provider.setter
    def known_identifier_provider(self, known_identifier_provider: str):
        """Sets the known_identifier_provider of this Traveler.

        provider for personal information. Can be a URI or identifier.  # noqa: E501

        :param known_identifier_provider: The known_identifier_provider of this Traveler.
        :type known_identifier_provider: str
        """

        self._known_identifier_provider = known_identifier_provider
