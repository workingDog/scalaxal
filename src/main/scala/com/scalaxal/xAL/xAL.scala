/*
 * Copyright (c) 2013, Ringo Wathelet
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright notice, this
 *   list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 * - Neither the name of "scalaxal" nor the names of its contributors may
 *   be used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.scalaxal.xAL

import javax.xml.namespace.QName

/**
 * Author: Ringo Wathelet Feb 2013
 *
 * xAL: eXtensible Address Language
 *
 * References:
 * 1) The Organization for the Advancement of Structured Information Standards [OASIS], https://www.oasis-open.org/committees/ciq/
 * 2) Extensible Address Language (xAL) Standard Description Document for W3C DTD/Schema Version 2.0
 */


object AddressDetailsTypeSet extends Enumeration {
  type AddressDetailsTypeSet = Value
  val Address, AddressLines, Country, AdministrativeArea, Locality, Thoroughfare = Value
}

object CountryTypeSet extends Enumeration {
  type CountryTypeSet = Value
  val AdministrativeArea, Locality, Thoroughfare = Value
}

object DependentLocalityTypeSet extends Enumeration {
  type DependentLocalityTypeSet = Value
  val LargeMailUser, PostalRoute, PostOffice, PostBox = Value
}

object AdministrativeAreaTypeSet extends Enumeration {
  type AdministrativeAreaTypeSet = Value
  val Locality, PostOffice, PostalCode = Value
}

object LocalityTypeSet extends Enumeration {
  type LocalityTypeSet = Value
  val PostOffice, PostBox, PostalRoute, LargeMailUser = Value
}

object ThoroughfareTypeSet extends Enumeration {
  type ThoroughfareTypeSet = Value
  val PostalCode, Premise, DependentLocality, Firm = Value
}

object ThoroughfareNumberTypeSet extends Enumeration {
  type ThoroughfareNumberTypeSet = Value
  val ThoroughfareNumberRange, ThoroughfareNumber = Value
}

object PremiseTypeSet1 extends Enumeration {
  type PremiseTypeSet1 = Value
  val PremiseNumber, PremiseNumberRange, PremiseLocation, SubPremise, Firm = Value
}

object SubPremiseTypeSet extends Enumeration {
  type SubPremiseTypeSet = Value
  val SubPremiseNumber, SubPremiseLocation = Value
}

object PremiseTypeSet2 extends Enumeration {
  type PremiseTypeSet2 = Value
  val SubPremise, Firm = Value
}

object PremiseNumberTypeSet extends Enumeration {
  type PremiseNumberTypeSet = Value
  val PremiseNumber, PremiseNumberRange = Value
}

case class XAL(addressDetails: Seq[AddressDetails] = Nil,
               any: Seq[Any] = Nil,
               version: Option[String] = None,
               attributes: Option[Map[String, QName]] = None) {

  def this(addressDetails: AddressDetails) = this(Seq.empty :+ addressDetails)

  /**
   * returns a new XAL object with value added to addressDetails sequence
   * @param value the AddressDetails to add to addressDetails
   * @return a new XAL object with value added to the addressDetails sequence
   */
  def addToAddressDetails(value: AddressDetails) = {
    this.copy(addressDetails = if (addressDetails == Nil) (Seq.empty :+ value) else (addressDetails :+ value))
  }

}


case class Content(content: Option[String] = None,
                   objectType: Option[String] = None,
                   code: Option[String] = None,
                   attributes: Option[Map[String, QName]] = None) {

  def this(content: String) = this(Option(content))
  def this(content: String, objectType: String, code: String) = this(Option(content),Option(objectType),Option(code))
}

case class AddressIdentifier(content: Option[String] = None,
                             identifierType: Option[String] = None,
                             objectType: Option[String] = None,
                             code: Option[String] = None,
                             attributes: Option[Map[String, QName]] = None) {

  def this(content: String) = this(Option(content))

}

case class SortingCode(objectType: Option[String] = None, code: Option[String] = None)

case class PostalServiceElements(addressIdentifier: Seq[AddressIdentifier] = Nil,
                                 endorsementLineCode: Option[Content] = None,
                                 keyLineCode: Option[Content] = None,
                                 barcode: Option[Content] = None,
                                 sortingCode: Option[SortingCode] = None,
                                 addressLatitude: Option[Content] = None,
                                 addressLatitudeDirection: Option[Content] = None,
                                 addressLongitude: Option[Content] = None,
                                 addressLongitudeDirection: Option[Content] = None,
                                 supplementaryPostalServiceData: Seq[Content] = Nil,
                                 any: Seq[Any] = Nil,
                                 objectType: Option[String] = None,
                                 attributes: Option[Map[String, QName]] = None) {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressIdentifier(value: AddressIdentifier) = {
    this.copy(addressIdentifier = if (addressIdentifier == Nil) (Seq.empty :+ value) else (addressIdentifier :+ value))
  }

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToSupplementaryPostalServiceData(value: Content) = {
    this.copy(supplementaryPostalServiceData = if (supplementaryPostalServiceData == Nil) (Seq.empty :+ value) else (supplementaryPostalServiceData :+ value))
  }
}


case class Address(content: Option[String] = None,
                   objectType: Option[String] = None,
                   code: Option[String] = None,
                   attributes: Option[Map[String, QName]] = None) extends AddressDetailsType {

  def this(content: String) = this(Option(content))
  def this(content: String, objectType: String, code: String) = this(Option(content),Option(objectType),Option(code))

}

case class CountryNameCode(content: Option[String] = None,
                           scheme: Option[String] = None,
                           code: Option[String] = None,
                           attributes: Option[Map[String, QName]] = None) {

  def this(content: String) = this(Option(content))

}


case class Country(addressLine: Seq[AddressLine] = Nil,
                   countryNameCode: Seq[CountryNameCode] = Nil,
                   countryName: Seq[Content] = Nil,
                   countryType: Option[CountryType] = None,
                   any: Seq[Any] = Nil,
                   attributes: Option[Map[String, QName]] = None) extends AddressDetailsType {

  def this(addressLine: AddressLine) = this(Seq.empty :+ addressLine)
  def this(addressLine: AddressLine, countryName: String) =
    this((Seq.empty :+ addressLine), Nil, (Seq.empty :+ new Content(countryName)))
  def this(addressLine: AddressLine, countryName: String, countryNameCode: String) =
    this((Seq.empty :+ addressLine), (Seq.empty :+ new CountryNameCode(countryNameCode)),
      (Seq.empty :+ new Content(countryName)))

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToCountryNameCode(value: CountryNameCode) = {
    this.copy(countryNameCode = if (countryNameCode == Nil) (Seq.empty :+ value) else (countryNameCode :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToCountryName(value: Content) = {
    this.copy(countryName = if (countryName == Nil) (Seq.empty :+ value) else (countryName :+ value))
  }

}

trait CountryType

case class AddressDetails(postalServiceElements: Option[PostalServiceElements] = None,
                          addressDetailsType: Option[AddressDetailsType] = None,
                          addressType: Option[String] = None,
                          currentStatus: Option[String] = None,
                          validFromDate: Option[String] = None,
                          validToDate: Option[String] = None,
                          usage: Option[String] = None,
                          code: Option[String] = None,
                          addressDetailsKey: Option[String] = None,
                          attributes: Option[Map[String, QName]] = None,
                          any: Seq[Any] = Nil)     {

  def this(addressDetailsType: AddressDetailsType) = this(None, Option(addressDetailsType))

}

trait AddressDetailsType

case class AddressLines(addressLines: Seq[AddressLine] = Nil,
                        any: Seq[Any] = Nil,
                        attributes: Option[Map[String, QName]] = None) extends AddressDetailsType {

  def this(addressLine: AddressLine) = this(Seq.empty :+ addressLine)

  /**
   * returns a new object with value added to addressLines sequence
   * @param value the AddressLine to add to addressLines
   * @return a new AddressLines object with value added to the addressLines sequence
   */
  def addToAddressLines(value: AddressLine) = {
    this.copy(addressLines = if (addressLines == Nil) (Seq.empty :+ value) else (addressLines :+ value))
  }

}

trait TypeOccurrence

object TypeOccurrence {
  def fromString(value: String): TypeOccurrence = value match {
    case "Before" => Before
    case "After" => After

  }
}

case object Before extends TypeOccurrence {
  override def toString = "Before"
}

case object After extends TypeOccurrence {
  override def toString = "After"
}


case class BuildingName(content: Option[String] = None,
                        objectType: Option[String] = None,
                        typeOccurrence: Option[TypeOccurrence] = None,
                        code: Option[String] = None,
                        attributes: Option[Map[String, QName]] = None) {

  def this(content: String) = this(Option(content))

}


case class DependentLocalityNumber(content: Option[String] = None,
                                   nameNumberOccurrence: Option[TypeOccurrence] = None,
                                   code: Option[String] = None,
                                   attributes: Option[Map[String, QName]] = None) {

  def this(content: String) = this(Option(content))

}


case class DependentLocality(addressLine: Seq[AddressLine] = Nil,
                             dependentLocalityName: Seq[Content] = Nil,
                             dependentLocalityNumber: Option[DependentLocalityNumber] = None,
                             dependentLocalityType: Option[DependentLocalityType] = None,
                             thoroughfare: Option[Thoroughfare] = None,
                             premise: Option[Premise] = None,
                             dependentLocality: Option[DependentLocality] = None,
                             postalCode: Option[PostalCode] = None,
                             any: Seq[Any] = Nil,
                             objectType: Option[String] = None,
                             usageType: Option[String] = None,
                             connector: Option[String] = None,
                             indicator: Option[String] = None,
                             attributes: Option[Map[String, QName]] = None) extends ThoroughfareType  {

  def this(addressLine: AddressLine) = this(Seq.empty :+ addressLine)

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToDependentLocalityName(value: Content) = {
    this.copy(dependentLocalityName = if (dependentLocalityName == Nil) (Seq.empty :+ value) else (dependentLocalityName :+ value))
  }
}


trait DependentLocalityType

case class Firm(addressLine: Seq[AddressLine] = Nil,
                    firmName: Seq[Content] = Nil,
                    department: Seq[Department] = Nil,
                    mailStop: Option[MailStop] = None,
                    postalCode: Option[PostalCode] = None,
                    any: Seq[Any] = Nil,
                    objectType: Option[String] = None,
                    attributes: Option[Map[String, QName]] = None) extends ThoroughfareType with PremiseType with PremiseType2 {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToFirmName(value: Content) = {
    this.copy(firmName = if (firmName == Nil) (Seq.empty :+ value) else (firmName :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToDepartment(value: Department) = {
    this.copy(department = if (department == Nil) (Seq.empty :+ value) else (department :+ value))
  }
}

case class LargeMailUserIdentifier(content: Option[String] = None,
                                   objectType: Option[String] = None,
                                   indicator: Option[String] = None,
                                   code: Option[String] = None,
                                   attributes: Option[Map[String, QName]] = None)  {

  def this(content: String) = this(Option(content))

}

case class LargeMailUser(addressLine: Seq[AddressLine] = Nil,
                             largeMailUserName: Seq[Content] = Nil,
                             largeMailUserIdentifier: Option[LargeMailUserIdentifier] = None,
                             buildingName: Seq[BuildingName] = Nil,
                             department: Option[Department] = None,
                             postBox: Option[PostBox] = None,
                             thoroughfare: Option[Thoroughfare] = None,
                             postalCode: Option[PostalCode] = None,
                             any: Seq[Any] = Nil,
                             objectType: Option[String] = None,
                             attributes: Option[Map[String, QName]] = None) extends DependentLocalityType with LocalityType {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToLargeMailUserName(value: Content) = {
    this.copy(largeMailUserName = if (largeMailUserName == Nil) (Seq.empty :+ value) else (largeMailUserName :+ value))
  }

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToBuildingName(value: BuildingName) = {
    this.copy(buildingName = if (buildingName == Nil) (Seq.empty :+ value) else (buildingName :+ value))
  }

}


case class MailStopNumber(content: Option[String] = None,
                          nameNumberSeparator: Option[String] = None,
                          code: Option[String] = None,
                          attributes: Option[Map[String, QName]] = None)  {

  def this(content: String) = this(Option(content))

}

case class MailStop(addressLine: Seq[AddressLine] = Nil,
                        mailStopName: Option[Content] = None,
                        mailStopNumber: Option[MailStopNumber] = None,
                        any: Seq[Any] = Nil,
                        objectType: Option[String] = None,
                        attributes: Option[Map[String, QName]] = None)  {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }

}


case class PostalRoute(addressLine: Seq[AddressLine] = Nil,
                           postalRouteName: Option[Content] = None,
                           postalRouteNumber: Option[Content] = None,
                           postBox: Option[PostBox] = None,
                           any: Seq[Any] = Nil,
                           objectType: Option[String] = None,
                           attributes: Option[Map[String, QName]] = None) extends DependentLocalityType with LocalityType {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }

}

case class SubPremiseName(content: Option[String] = None,
                          objectType: Option[String] = None,
                          typeOccurrence: Option[TypeOccurrence] = None,
                          code: Option[String] = None,
                          attributes: Option[Map[String, QName]] = None)  {

  def this(content: String) = this(Option(content))

}



case class SubPremiseLocation(content: Option[String] = None,
                              code: Option[String] = None) extends SubPremiseType  {

  def this(content: String) = this(Option(content))

}

case class SubPremiseNumber(content: Option[String] = None,
                            indicator: Option[String] = None,
                            indicatorOccurrence: Option[TypeOccurrence] = None,
                            numberOccurrence: Option[TypeOccurrence] = None,
                            premiseNumberSeparator: Option[String] = None,
                            objectType: Option[String] = None,
                            code: Option[String] = None,
                            attributes: Option[Map[String, QName]] = None) extends SubPremiseType  {

  def this(content: String) = this(Option(content))

}


case class SubPremiseNumberPrefix(content: Option[String] = None,
                                  numberPrefixSeparator: Option[String] = None,
                                  objectType: Option[String] = None,
                                  code: Option[String] = None,
                                  attributes: Option[Map[String, QName]] = None)  {

  def this(content: String) = this(Option(content))

}

case class SubPremiseNumberSuffix(content: Option[String] = None,
                                  numberSuffixSeparator: Option[String] = None,
                                  objectType: Option[String] = None,
                                  code: Option[String] = None,
                                  attributes: Option[Map[String, QName]] = None)  {

  def this(content: String) = this(Option(content))

}

case class SubPremise(addressLine: Seq[AddressLine] = Nil,
                          subPremiseName: Seq[SubPremiseName] = Nil,
                          subPremiseType: Seq[SubPremiseType] = Nil,
                          subPremiseNumberPrefix: Seq[SubPremiseNumberPrefix] = Nil,
                          subPremiseNumberSuffix: Seq[SubPremiseNumberSuffix] = Nil,
                          buildingName: Seq[BuildingName] = Nil,
                          firm: Option[Firm] = None,
                          mailStop: Option[MailStop] = None,
                          postalCode: Option[PostalCode] = None,
                          subPremise: Option[SubPremise] = None,
                          any: Seq[Any] = Nil,
                          objectType: Option[String] = None,
                          attributes: Option[Map[String, QName]] = None) extends PremiseType with PremiseType2 {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToSubPremiseName(value: SubPremiseName) = {
    this.copy(subPremiseName = if (subPremiseName == Nil) (Seq.empty :+ value) else (subPremiseName :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToSubPremiseType(value: SubPremiseType) = {
    this.copy(subPremiseType = if (subPremiseType == Nil) (Seq.empty :+ value) else (subPremiseType :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToSubPremiseNumberPrefix(value: SubPremiseNumberPrefix) = {
    this.copy(subPremiseNumberPrefix = if (subPremiseNumberPrefix == Nil) (Seq.empty :+ value) else (subPremiseNumberPrefix :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToSubPremiseNumberSuffix(value: SubPremiseNumberSuffix) = {
    this.copy(subPremiseNumberSuffix = if (subPremiseNumberSuffix == Nil) (Seq.empty :+ value) else (subPremiseNumberSuffix :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToBuildingName(value: BuildingName) = {
    this.copy(buildingName = if (buildingName == Nil) (Seq.empty :+ value) else (buildingName :+ value))
  }

}

trait SubPremiseType


case class AddressLine(content: Option[String] = None,
                       objectType: Option[String] = None,
                       code: Option[String] = None,
                       attributes: Option[Map[String, QName]] = None)  {

  def this(content: String) = this(Option(content))

}

case class Locality(addressLine: Seq[AddressLine] = Nil,
                    localityName: Seq[Content] = Nil,
                    localityType: Option[LocalityType] = None,
                    thoroughfare: Option[Thoroughfare] = None,
                    premise: Option[Premise] = None,
                    dependentLocality: Option[DependentLocality] = None,
                    postalCode: Option[PostalCode] = None,
                    any: Seq[Any] = Nil,
                    objectType: Option[String] = None,
                    usageType: Option[String] = None,
                    indicator: Option[String] = None,
                    attributes: Option[Map[String, QName]] = None) extends CountryType with AddressDetailsType with AdministrativeAreaType {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToLocalityName(value: Content) = {
    this.copy(localityName = if (localityName == Nil) (Seq.empty :+ value) else (localityName :+ value))
  }

}
trait LocalityType

trait DependentThoroughfares

object DependentThoroughfares {
  def fromString(value: String): DependentThoroughfares = value match {
    case "Yes" => Yes
    case "No" => No

  }
}

case object Yes extends DependentThoroughfares {
  override def toString = "Yes"
}

case object No extends DependentThoroughfares {
  override def toString = "No"
}

trait RangeType

object RangeType {
  def fromString(value: String): RangeType = value match {
    case "Odd" => Odd
    case "Even" => Even

  }
}

case object Odd extends RangeType {
  override def toString = "Odd"
}

case object Even extends RangeType {
  override def toString = "Even"
}

case class ThoroughfareNumberFrom(addressLine: Seq[AddressLine] = Nil,
                                  thoroughfareNumberType: Seq[ThoroughfareNumberType] = Nil,
                                  thoroughfareNumberPrefix: Seq[ThoroughfareNumberPrefix] = Nil,
                                  thoroughfareNumberSuffix: Seq[ThoroughfareNumberSuffix] = Nil,
                                  code: Option[String] = None) {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToThoroughfareNumberType(value: ThoroughfareNumberType) = {
    this.copy(thoroughfareNumberType = if (thoroughfareNumberType == Nil) (Seq.empty :+ value) else (thoroughfareNumberType :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToThoroughfareNumberPrefix(value: ThoroughfareNumberPrefix) = {
    this.copy(thoroughfareNumberPrefix = if (thoroughfareNumberPrefix == Nil) (Seq.empty :+ value) else (thoroughfareNumberPrefix :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToThoroughfareNumberSuffix(value: ThoroughfareNumberSuffix) = {
    this.copy(thoroughfareNumberSuffix = if (thoroughfareNumberSuffix == Nil) (Seq.empty :+ value) else (thoroughfareNumberSuffix :+ value))
  }
}


case class ThoroughfareNumberTo(addressLine: Seq[AddressLine] = Nil,
                                  thoroughfareNumberType: Seq[ThoroughfareNumberType] = Nil,
                                  thoroughfareNumberPrefix: Seq[ThoroughfareNumberPrefix] = Nil,
                                  thoroughfareNumberSuffix: Seq[ThoroughfareNumberSuffix] = Nil,
                                  code: Option[String] = None) {
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToThoroughfareNumberType(value: ThoroughfareNumberType) = {
    this.copy(thoroughfareNumberType = if (thoroughfareNumberType == Nil) (Seq.empty :+ value) else (thoroughfareNumberType :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToThoroughfareNumberPrefix(value: ThoroughfareNumberPrefix) = {
    this.copy(thoroughfareNumberPrefix = if (thoroughfareNumberPrefix == Nil) (Seq.empty :+ value) else (thoroughfareNumberPrefix :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToThoroughfareNumberSuffix(value: ThoroughfareNumberSuffix) = {
    this.copy(thoroughfareNumberSuffix = if (thoroughfareNumberSuffix == Nil) (Seq.empty :+ value) else (thoroughfareNumberSuffix :+ value))
  }
}


case class ThoroughfareNumberRange(addressLine: Seq[AddressLine] = Nil,
                                   thoroughfareNumberFrom: Option[ThoroughfareNumberFrom],
                                   thoroughfareNumberTo: Option[ThoroughfareNumberTo],
                                   rangeType: Option[RangeType] = None,
                                   indicator: Option[String] = None,
                                   separator: Option[String] = None,
                                   indicatorOccurrence: Option[TypeOccurrence] = None,
                                   numberRangeOccurrence: Option[NumberOccurrence] = None,
                                   objectType: Option[String] = None,
                                   code: Option[String] = None,
                                   attributes: Option[Map[String, QName]] = None) extends ThoroughfareNumberType {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
}

case class DependentThoroughfare(addressLine: Seq[AddressLine] = Nil,
                                 thoroughfarePreDirection: Option[Content] = None,
                                 thoroughfareLeadingType: Option[Content] = None,
                                 thoroughfareName: Seq[Content] = Nil,
                                 thoroughfareTrailingType: Option[Content] = None,
                                 thoroughfarePostDirection: Option[Content] = None,
                                 any: Seq[Any] = Nil,
                                 objectType: Option[String] = None,
                                 attributes: Option[Map[String, QName]] = None) {
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToThoroughfareName(value: Content) = {
    this.copy(thoroughfareName = if (thoroughfareName == Nil) (Seq.empty :+ value) else (thoroughfareName :+ value))
  }
}

case class Thoroughfare(addressLine: Seq[AddressLine] = Nil,
                        thoroughfareNumberType: Seq[ThoroughfareNumberType] = Nil,
                        thoroughfareNumberPrefix: Seq[ThoroughfareNumberPrefix] = Nil,
                        thoroughfareNumberSuffix: Seq[ThoroughfareNumberSuffix] = Nil,
                        thoroughfarePreDirection: Option[Content] = None,
                        thoroughfareLeadingType: Option[Content] = None,
                        thoroughfareName: Seq[Content] = Nil,
                        thoroughfareTrailingType: Option[Content] = None,
                        thoroughfarePostDirection: Option[Content] = None,
                        dependentThoroughfare: Option[DependentThoroughfare] = None,
                        thoroughfareType: Option[ThoroughfareType] = None,
                        any: Seq[Any] = Nil,
                        objectType: Option[String] = None,
                        dependentThoroughfares: Option[DependentThoroughfares] = None,
                        dependentThoroughfaresIndicator: Option[String] = None,
                        dependentThoroughfaresConnector: Option[String] = None,
                        dependentThoroughfaresType: Option[String] = None,
                        attributes: Option[Map[String, QName]] = None) extends CountryType with AddressDetailsType {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToThoroughfareNumberType(value: ThoroughfareNumberType) = {
    this.copy(thoroughfareNumberType = if (thoroughfareNumberType == Nil) (Seq.empty :+ value) else (thoroughfareNumberType :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToThoroughfareNumberPrefix(value: ThoroughfareNumberPrefix) = {
    this.copy(thoroughfareNumberPrefix = if (thoroughfareNumberPrefix == Nil) (Seq.empty :+ value) else (thoroughfareNumberPrefix :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToThoroughfareNumberSuffix(value: ThoroughfareNumberSuffix) = {
    this.copy(thoroughfareNumberSuffix = if (thoroughfareNumberSuffix == Nil) (Seq.empty :+ value) else (thoroughfareNumberSuffix :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToThoroughfareName(value: Content) = {
    this.copy(thoroughfareName = if (thoroughfareName == Nil) (Seq.empty :+ value) else (thoroughfareName :+ value))
  }
}

trait ThoroughfareType
trait ThoroughfareNumberType

case class SubAdministrativeArea(addressLine: Seq[AddressLine] = Nil,
                                 subAdministrativeAreaName: Seq[Content] = Nil,
                                 subAdministrativeAreaType: Option[AdministrativeAreaType] = None,
                                 any: Seq[Any] = Nil,
                                 objectType: Option[String] = None,
                                 usageType: Option[String] = None,
                                 indicator: Option[String] = None,
                                 attributes: Option[Map[String, QName]] = None) {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToSubAdministrativeAreaName(value: Content) = {
    this.copy(subAdministrativeAreaName = if (subAdministrativeAreaName == Nil) (Seq.empty :+ value) else (subAdministrativeAreaName :+ value))
  }
}

case class AdministrativeArea(addressLine: Seq[AddressLine] = Nil,
                              administrativeAreaName: Seq[Content] = Nil,
                              subAdministrativeArea: Option[SubAdministrativeArea] = None,
                              administrativeAreaType: Option[AdministrativeAreaType] = None,
                              any: Seq[Any] = Nil,
                              objectType: Option[String] = None,
                              usageType: Option[String] = None,
                              indicator: Option[String] = None,
                              attributes: Option[Map[String, QName]] = None) extends CountryType with AddressDetailsType {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAdministrativeAreaName(value: Content) = {
    this.copy(administrativeAreaName = if (administrativeAreaName == Nil) (Seq.empty :+ value) else (administrativeAreaName :+ value))
  }

}

trait AdministrativeAreaType

case class PostOfficeNumber(content: Option[String] = None,
                            indicator: Option[String] = None,
                            indicatorOccurrence: Option[TypeOccurrence] = None,
                            code: Option[String] = None,
                            attributes: Option[Map[String, QName]] = None)  {

  def this(content: String) = this(Option(content))
  def this(content: String, indicator: String) = this(Option(content), Option(indicator))
}

case class PostOffice(addressLine: Seq[AddressLine] = Nil,
                      postOfficeNumber: Option[PostOfficeNumber] = None,  // was Seq but ref indicates (0 or 1)
                      postOfficeName: Seq[Content] = Nil,
                      postalRoute: Option[PostalRoute] = None,
                      postBox: Option[PostBox] = None,
                      postalCode: Option[PostalCode] = None,
                      any: Seq[Any] = Nil,
                      objectType: Option[String] = None,
                      indicator: Option[String] = None,
                      attributes: Option[Map[String, QName]] = None) extends DependentLocalityType with LocalityType with AdministrativeAreaType {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPostOfficeName(value: Content) = {
    this.copy(postOfficeName = if (postOfficeName == Nil) (Seq.empty :+ value) else (postOfficeName :+ value))
  }

}

case class PostalCodeNumberExtension(content: Option[String] = None,
                                     objectType: Option[String] = None,
                                     numberExtensionSeparator: Option[String] = None,
                                     code: Option[String] = None,
                                     attributes: Option[Map[String, QName]] = None)  {

  def this(content: String) = this(Option(content))

}

case class PostTownSuffix(content: Option[String] = None,
                          code: Option[String] = None,
                          attributes: Option[Map[String, QName]] = None)  {

  def this(content: String) = this(Option(content))

}

case class PostTown(addressLine: Seq[AddressLine] = Nil,
                    postTownName: Seq[Content] = Nil,
                    postTownSuffix: Option[PostTownSuffix] = None,
                    objectType: Option[String] = None,
                    attributes: Option[Map[String, QName]] = None) {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPostTownName(value: Content) = {
    this.copy(postTownName = if (postTownName == Nil) (Seq.empty :+ value) else (postTownName :+ value))
  }
}


case class PostalCode(addressLine: Seq[AddressLine] = Nil,
                      postalCodeNumber: Seq[Content] = Nil,
                      postalCodeNumberExtension: Seq[PostalCodeNumberExtension] = Nil,
                      postTown: Option[PostTown] = None,
                      any: Seq[Any] = Nil,
                      objectType: Option[String] = None,
                      attributes: Option[Map[String, QName]] = None) extends ThoroughfareType with AdministrativeAreaType {

  def this(postalCodeNumber: Content) = this(Nil, (Seq.empty :+ postalCodeNumber))

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPostalCodeNumber(value: Content) = {
    this.copy(postalCodeNumber = if (postalCodeNumber == Nil) (Seq.empty :+ value) else (postalCodeNumber :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPostalCodeNumberExtension(value: PostalCodeNumberExtension) = {
    this.copy(postalCodeNumberExtension = if (postalCodeNumberExtension == Nil) (Seq.empty :+ value) else (postalCodeNumberExtension :+ value))
  }
}

case class PostBoxNumber(content: Option[String] = None,
                         code: Option[String] = None,
                         objectType: Option[String] = None,
                         attributes: Option[Map[String, QName]] = None)   {

  def this(content: String) = this(Option(content))

}


case class PostBoxNumberPrefix(content: Option[String] = None,
                               numberPrefixSeparator: Option[String] = None,
                               code: Option[String] = None,
                               attributes: Option[Map[String, QName]] = None) {

  def this(content: String) = this(Option(content))

}

case class PostBoxNumberSuffix(content: Option[String] = None,
                               numberSuffixSeparator: Option[String] = None,
                               code: Option[String] = None,
                               attributes: Option[Map[String, QName]] = None)  {

  def this(content: String) = this(Option(content))

}

case class PostBoxNumberExtension(content: Option[String] = None,
                                  numberExtensionSeparator: Option[String] = None,
                                  code: Option[String] = None,
                                  attributes: Option[Map[String, QName]] = None)  {

  def this(content: String) = this(Option(content))

}

case class PostBox(addressLine: Seq[AddressLine] = Nil,
                   postBoxNumber: PostBoxNumber,
                   postBoxNumberPrefix: Option[PostBoxNumberPrefix] = None,
                   postBoxNumberSuffix: Option[PostBoxNumberSuffix] = None,
                   postBoxNumberExtension: Option[PostBoxNumberExtension] = None,
                   firm: Option[Firm] = None,
                   postalCode: Option[PostalCode] = None,
                   any: Seq[Any] = Nil,
                   objectType: Option[String] = None,
                   indicator: Option[String] = None,
                   attributes: Option[Map[String, QName]] = None) extends DependentLocalityType with LocalityType {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }

}

case class Department(addressLine: Seq[AddressLine] = Nil,
                      departmentName: Seq[Content] = Nil,
                      mailStop: Option[MailStop] = None,
                      postalCode: Option[PostalCode] = None,
                      any: Seq[Any] = Nil,
                      objectType: Option[String] = None,
                      attributes: Option[Map[String, QName]] = None) {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToDepartmentName(value: Content) = {
    this.copy(departmentName = if (departmentName == Nil) (Seq.empty :+ value) else (departmentName :+ value))
  }
}

case class PremiseName(content: Option[String] = None,
                       objectType: Option[String] = None,
                       typeOccurrence: Option[TypeOccurrence] = None,
                       code: Option[String] = None,
                       attributes: Option[Map[String, QName]] = None)  {

  def this(content: String) = this(Option(content))

}

case class PremiseLocation(content: Option[String] = None,
                           code: Option[String] = None,
                           attributes: Option[Map[String, QName]] = None) extends PremiseType  {

  def this(content: String) = this(Option(content))

}

case class PremiseNumberRangeFrom(addressLine: Seq[AddressLine] = Nil,
                                  premiseNumberPrefix: Seq[PremiseNumberPrefix] = Nil,
                                  premiseNumber: Seq[PremiseNumber] = Nil,
                                  premiseNumberSuffix: Seq[PremiseNumberSuffix] = Nil) {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPremiseNumberPrefix(value: PremiseNumberPrefix) = {
    this.copy(premiseNumberPrefix = if (premiseNumberPrefix == Nil) (Seq.empty :+ value) else (premiseNumberPrefix :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPremiseNumberSuffix(value: PremiseNumberSuffix) = {
    this.copy(premiseNumberSuffix = if (premiseNumberSuffix == Nil) (Seq.empty :+ value) else (premiseNumberSuffix :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPremiseNumber(value: PremiseNumber) = {
    this.copy(premiseNumber = if (premiseNumber == Nil) (Seq.empty :+ value) else (premiseNumber :+ value))
  }
}


case class PremiseNumberRangeTo(addressLine: Seq[AddressLine] = Nil,
                                premiseNumberPrefix: Seq[PremiseNumberPrefix] = Nil,
                                premiseNumber: Seq[PremiseNumber] = Nil,
                                premiseNumberSuffix: Seq[PremiseNumberSuffix] = Nil) {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPremiseNumberPrefix(value: PremiseNumberPrefix) = {
    this.copy(premiseNumberPrefix = if (premiseNumberPrefix == Nil) (Seq.empty :+ value) else (premiseNumberPrefix :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPremiseNumberSuffix(value: PremiseNumberSuffix) = {
    this.copy(premiseNumberSuffix = if (premiseNumberSuffix == Nil) (Seq.empty :+ value) else (premiseNumberSuffix :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPremiseNumber(value: PremiseNumber) = {
    this.copy(premiseNumber = if (premiseNumber == Nil) (Seq.empty :+ value) else (premiseNumber :+ value))
  }
}


case class PremiseNumberRange(premiseNumberRangeFrom: Option[PremiseNumberRangeFrom],
                              premiseNumberRangeTo: Option[PremiseNumberRangeTo],
                              rangeType: Option[String] = None,
                              indicator: Option[String] = None,
                              separator: Option[String] = None,
                              objectType: Option[String] = None,
                              indicatorOccurrence: Option[TypeOccurrence] = None,
                              numberRangeOccurrence: Option[NumberOccurrence] = None) extends PremiseNumberType

case class Premise(addressLine: Seq[AddressLine] = Nil,
                   premiseName: Seq[PremiseName] = Nil,
                   premiseLocation: Seq[PremiseLocation] = Nil,
                   premiseNumber: Option[PremiseNumber] = None,
                   premiseNumberRange: Seq[PremiseNumberRange] = Nil,
                   premiseNumberPrefix: Seq[PremiseNumberPrefix] = Nil,
                   premiseNumberSuffix: Seq[PremiseNumberSuffix] = Nil,
                   buildingName: Seq[BuildingName] = Nil,
                   premiseFirmOrSubPremiseType: Seq[PremiseType2] = Nil,
                   mailStop: Option[MailStop] = None,
                   postalCode: Option[PostalCode] = None,
                   premise: Option[Premise] = None,
                   any: Seq[Any] = Nil,
                   objectType: Option[String] = None,
                   premiseDependency: Option[String] = None,
                   premiseDependencyType: Option[String] = None,
                   premiseThoroughfareConnector: Option[String] = None,
                   attributes: Option[Map[String, QName]] = None) extends ThoroughfareType {

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPremiseFirmOrSubPremiseType(value: PremiseType2) = {
    this.copy(premiseFirmOrSubPremiseType = if (premiseFirmOrSubPremiseType == Nil) (Seq.empty :+ value) else (premiseFirmOrSubPremiseType :+ value))
  }

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPremiseLocation(value: PremiseLocation) = {
    this.copy(premiseLocation = if (premiseLocation == Nil) (Seq.empty :+ value) else (premiseLocation :+ value))
  }

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPremiseName(value: PremiseName) = {
    this.copy(premiseName = if (premiseName == Nil) (Seq.empty :+ value) else (premiseName :+ value))
  }

  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToAddressLine(value: AddressLine) = {
    this.copy(addressLine = if (addressLine == Nil) (Seq.empty :+ value) else (addressLine :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPremiseNumberPrefix(value: PremiseNumberPrefix) = {
    this.copy(premiseNumberPrefix = if (premiseNumberPrefix == Nil) (Seq.empty :+ value) else (premiseNumberPrefix :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPremiseNumberSuffix(value: PremiseNumberSuffix) = {
    this.copy(premiseNumberSuffix = if (premiseNumberSuffix == Nil) (Seq.empty :+ value) else (premiseNumberSuffix :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToBuildingName(value: BuildingName) = {
    this.copy(buildingName = if (buildingName == Nil) (Seq.empty :+ value) else (buildingName :+ value))
  }
  /**
   * returns a new object with value added to the sequence
   * @param value to add
   * @return a new object with value added to the sequence
   */
  def addToPremiseNumberRange(value: PremiseNumberRange) = {
    this.copy(premiseNumberRange = if (premiseNumberRange == Nil) (Seq.empty :+ value) else (premiseNumberRange :+ value))
  }
}

trait PremiseType
trait PremiseType2
trait PremiseNumberType

/** A-12 where 12 is number and A is prefix and "-" is the separator
  */
case class ThoroughfareNumberPrefix(content: Option[String] = None,
                                    numberPrefixSeparator: Option[String] = None,
                                    objectType: Option[String] = None,
                                    code: Option[String] = None,
                                    attributes: Option[Map[String, QName]] = None)   {

  def this(content: String) = this(Option(content))

}

case class ThoroughfareNumberSuffix(content: Option[String] = None,
                                    numberSuffixSeparator: Option[String] = None,
                                    objectType: Option[String] = None,
                                    code: Option[String] = None,
                                    attributes: Option[Map[String, QName]] = None)  {

  def this(content: String) = this(Option(content))

}
trait NumberType

object NumberType {
  def fromString(value: String): NumberType = value match {
    case "Single" => Single
    case "Range" => Range

  }
}

case object Single extends NumberType {
  override def toString = "Single"
}

case object Range extends NumberType {
  override def toString = "Range"
}

trait NumberOccurrence

object NumberOccurrence {
  def fromString(value: String): NumberOccurrence = value match {
    case "BeforeName" => BeforeName
    case "AfterName" => AfterName
    case "BeforeType" => BeforeType
    case "AfterType" => AfterType

  }
}

case object BeforeName extends NumberOccurrence {
  override def toString = "BeforeName"
}

case object AfterName extends NumberOccurrence {
  override def toString = "AfterName"
}

case object BeforeType extends NumberOccurrence {
  override def toString = "BeforeType"
}

case object AfterType extends NumberOccurrence {
  override def toString = "AfterType"
}


case class ThoroughfareNumber(content: Option[String] = None,
                              numberType: Option[NumberType] = None,
                              objectType: Option[String] = None,
                              indicator: Option[String] = None,
                              indicatorOccurrence: Option[TypeOccurrence] = None,
                              numberOccurrence: Option[NumberOccurrence] = None,
                              code: Option[String] = None,
                              attributes: Option[Map[String, QName]] = None) extends ThoroughfareNumberType  {

  def this(content: String) = this(Option(content))

}

case class PremiseNumber(content: Option[String] = None,
                         numberType: Option[NumberType] = None,
                         objectType: Option[String] = None,
                         indicator: Option[String] = None,
                         indicatorOccurrence: Option[TypeOccurrence] = None,
                         numberTypeOccurrence: Option[TypeOccurrence] = None,
                         code: Option[String] = None,
                         attributes: Option[Map[String, QName]] = None) extends PremiseNumberType   {

  def this(content: String) = this(Option(content))

}

case class PremiseNumberPrefix(content: Option[String],
                               numberPrefixSeparator: Option[String] = None,
                               objectType: Option[String] = None,
                               code: Option[String] = None,
                               attributes: Option[Map[String, QName]] = None)   {

  def this(content: String) = this(Option(content))

}

case class PremiseNumberSuffix(content: Option[String] = None,
                               numberSuffixSeparator: Option[String] = None,
                               objectType: Option[String] = None,
                               code: Option[String] = None,
                               attributes: Option[Map[String, QName]] = None)  {

  def this(content: String) = this(Option(content))

}

case class GrPostal(code: Option[String] = None)

