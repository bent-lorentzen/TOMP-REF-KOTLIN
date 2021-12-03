# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server.models.place import Place  # noqa: F401,E501
from swagger_server import util


class SupportRequest(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    def __init__(self, id: str=None, support_type: str=None, location: Place=None, time: datetime=None, priority: str=None, contact_information_end_user: str=None, comment: str=None, requested_response_time: float=None):  # noqa: E501
        """SupportRequest - a model defined in Swagger

        :param id: The id of this SupportRequest.  # noqa: E501
        :type id: str
        :param support_type: The support_type of this SupportRequest.  # noqa: E501
        :type support_type: str
        :param location: The location of this SupportRequest.  # noqa: E501
        :type location: Place
        :param time: The time of this SupportRequest.  # noqa: E501
        :type time: datetime
        :param priority: The priority of this SupportRequest.  # noqa: E501
        :type priority: str
        :param contact_information_end_user: The contact_information_end_user of this SupportRequest.  # noqa: E501
        :type contact_information_end_user: str
        :param comment: The comment of this SupportRequest.  # noqa: E501
        :type comment: str
        :param requested_response_time: The requested_response_time of this SupportRequest.  # noqa: E501
        :type requested_response_time: float
        """
        self.swagger_types = {
            'id': str,
            'support_type': str,
            'location': Place,
            'time': datetime,
            'priority': str,
            'contact_information_end_user': str,
            'comment': str,
            'requested_response_time': float
        }

        self.attribute_map = {
            'id': 'id',
            'support_type': 'supportType',
            'location': 'location',
            'time': 'time',
            'priority': 'priority',
            'contact_information_end_user': 'contactInformationEndUser',
            'comment': 'comment',
            'requested_response_time': 'requestedResponseTime'
        }
        self._id = id
        self._support_type = support_type
        self._location = location
        self._time = time
        self._priority = priority
        self._contact_information_end_user = contact_information_end_user
        self._comment = comment
        self._requested_response_time = requested_response_time

    @classmethod
    def from_dict(cls, dikt) -> 'SupportRequest':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The supportRequest of this SupportRequest.  # noqa: E501
        :rtype: SupportRequest
        """
        return util.deserialize_model(dikt, cls)

    @property
    def id(self) -> str:
        """Gets the id of this SupportRequest.

        the booking id  # noqa: E501

        :return: The id of this SupportRequest.
        :rtype: str
        """
        return self._id

    @id.setter
    def id(self, id: str):
        """Sets the id of this SupportRequest.

        the booking id  # noqa: E501

        :param id: The id of this SupportRequest.
        :type id: str
        """

        self._id = id

    @property
    def support_type(self) -> str:
        """Gets the support_type of this SupportRequest.


        :return: The support_type of this SupportRequest.
        :rtype: str
        """
        return self._support_type

    @support_type.setter
    def support_type(self, support_type: str):
        """Sets the support_type of this SupportRequest.


        :param support_type: The support_type of this SupportRequest.
        :type support_type: str
        """
        allowed_values = ["BROKEN_DOWN", "NOT_AT_LOCATION", "MISSING_AFTER_PAUSE", "NOT_CLEAN", "NOT_AVAILABLE", "UNABLE_TO_OPEN", "UNABLE_TO_CLOSE", "API_TECHNICAL", "API_FUNCTIONAL", "ACCIDENT", "OTHER"]  # noqa: E501
        if support_type not in allowed_values:
            raise ValueError(
                "Invalid value for `support_type` ({0}), must be one of {1}"
                .format(support_type, allowed_values)
            )

        self._support_type = support_type

    @property
    def location(self) -> Place:
        """Gets the location of this SupportRequest.


        :return: The location of this SupportRequest.
        :rtype: Place
        """
        return self._location

    @location.setter
    def location(self, location: Place):
        """Sets the location of this SupportRequest.


        :param location: The location of this SupportRequest.
        :type location: Place
        """

        self._location = location

    @property
    def time(self) -> datetime:
        """Gets the time of this SupportRequest.


        :return: The time of this SupportRequest.
        :rtype: datetime
        """
        return self._time

    @time.setter
    def time(self, time: datetime):
        """Sets the time of this SupportRequest.


        :param time: The time of this SupportRequest.
        :type time: datetime
        """

        self._time = time

    @property
    def priority(self) -> str:
        """Gets the priority of this SupportRequest.

        the priority of the support request.  # noqa: E501

        :return: The priority of this SupportRequest.
        :rtype: str
        """
        return self._priority

    @priority.setter
    def priority(self, priority: str):
        """Sets the priority of this SupportRequest.

        the priority of the support request.  # noqa: E501

        :param priority: The priority of this SupportRequest.
        :type priority: str
        """
        allowed_values = ["ERROR_CANNOT_CONTINUE", "ERROR_CAN_CONTINUE", "DISTURBING_ISSUE", "QUESTION", "OTHER"]  # noqa: E501
        if priority not in allowed_values:
            raise ValueError(
                "Invalid value for `priority` ({0}), must be one of {1}"
                .format(priority, allowed_values)
            )

        self._priority = priority

    @property
    def contact_information_end_user(self) -> str:
        """Gets the contact_information_end_user of this SupportRequest.

        contact information of the end user in case of direct response requests, like phone number  # noqa: E501

        :return: The contact_information_end_user of this SupportRequest.
        :rtype: str
        """
        return self._contact_information_end_user

    @contact_information_end_user.setter
    def contact_information_end_user(self, contact_information_end_user: str):
        """Sets the contact_information_end_user of this SupportRequest.

        contact information of the end user in case of direct response requests, like phone number  # noqa: E501

        :param contact_information_end_user: The contact_information_end_user of this SupportRequest.
        :type contact_information_end_user: str
        """

        self._contact_information_end_user = contact_information_end_user

    @property
    def comment(self) -> str:
        """Gets the comment of this SupportRequest.


        :return: The comment of this SupportRequest.
        :rtype: str
        """
        return self._comment

    @comment.setter
    def comment(self, comment: str):
        """Sets the comment of this SupportRequest.


        :param comment: The comment of this SupportRequest.
        :type comment: str
        """

        self._comment = comment

    @property
    def requested_response_time(self) -> float:
        """Gets the requested_response_time of this SupportRequest.

        time to respond in minutes.  # noqa: E501

        :return: The requested_response_time of this SupportRequest.
        :rtype: float
        """
        return self._requested_response_time

    @requested_response_time.setter
    def requested_response_time(self, requested_response_time: float):
        """Sets the requested_response_time of this SupportRequest.

        time to respond in minutes.  # noqa: E501

        :param requested_response_time: The requested_response_time of this SupportRequest.
        :type requested_response_time: float
        """

        self._requested_response_time = requested_response_time
