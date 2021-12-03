# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server import util


class Scenario(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """

    """
    allowed enum values
    """
    POSTPONED_COMMIT = "POSTPONED_COMMIT"
    DEPOSIT = "DEPOSIT"
    PAY_WHEN_FINISHED = "PAY_WHEN_FINISHED"
    REQUIRE_BOOKING_DATA = "REQUIRE_BOOKING_DATA"
    RETURN_AREA = "RETURN_AREA"
    UPFRONT_PAYMENT = "UPFRONT_PAYMENT"
    def __init__(self):  # noqa: E501
        """Scenario - a model defined in Swagger

        """
        self.swagger_types = {
        }

        self.attribute_map = {
        }

    @classmethod
    def from_dict(cls, dikt) -> 'Scenario':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The scenario of this Scenario.  # noqa: E501
        :rtype: Scenario
        """
        return util.deserialize_model(dikt, cls)
