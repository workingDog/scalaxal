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
//import scala.reflect.runtime.{universe => ru}
import java.lang.annotation.{RetentionPolicy, Retention}

/**
 * Author: Ringo Wathelet Feb 2013
 *
 * Modified classes from the generated by <a href="http://scalaxb.org/">scalaxb</a>.
 *
 * References:
 * 1) The Organization for the Advancement of Structured Information Standards [OASIS], https://www.oasis-open.org/committees/ciq/
 * 2) Extensible Address Language (xAL) Standard Description Document for W3C DTD/Schema Version 2.0
 *
 */

/** xAL: eXtensible Address Language 
This is an XML document type definition (DTD) for
defining addresses.
Original Date of Creation: 1 March 2001
Copyright(c) 2000, OASIS. All Rights Reserved [http://www.oasis-open.org]
Contact: Customer Information Quality Technical Committee, OASIS
http://www.oasis-open.org/committees/ciq
VERSION: 2.0 [MAJOR RELEASE] Date of Creation: 01 May 2002
Last Update: 24 July 2002
Previous Version: 1.3
  */

class AttributeField() extends scala.annotation.StaticAnnotation

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

  def this() = this(Nil, Nil, None, None)

//------------------------------------------------------------------------------
//-----------testing some methods-----------------------------------------------
//------------------------------------------------------------------------------

  /**
   * returns a copy of the original XAL with the designated fieldName changed to the newValue
   *
   * @param fieldName the name of the field to change
   * @param newValue the new value to be in the filedName
   * @return a new object with the designated fieldName changed to the newValue
   */
  def change(fieldName: String, newValue: Any) = {
    val theCopy = this.copy()
    val field = theCopy.getClass.getDeclaredField(fieldName)
    field.setAccessible(true)
    field.set(theCopy, newValue)
    theCopy
  }

  /**
   * returns a new object with the newValue added to the designated Seq of fieldName
   * Note: no check is performed on the type compatibility
   *
   * @param fieldName the name of the field to change
   * @param newValue the new value to be in the fieldName Seq
   * @tparam A the type of the Seq element
   * @return a new object with the newValue added to the designated Seq of fieldName
   */
  def addTo[A](fieldName: String, newValue: A) = {
    val theCopy = this.copy()
    val field = theCopy.getClass.getDeclaredField(fieldName)
    field.setAccessible(true)
    val theSeq = field.get(theCopy).asInstanceOf[Seq[A]] // assume ok
    val newSeq = if (theSeq == Nil) (Seq.empty :+ newValue) else (theSeq :+ newValue)
    field.set(theCopy, newSeq)
    theCopy
  }
}
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------

case class Content(content: Option[String] = None,
                   objectType: Option[String] = None,
                   code: Option[String] = None,
                   attributes: Option[Map[String, QName]] = None)

case class AddressIdentifier(content: Option[String] = None,
                             identifierType: Option[String] = None,
                             objectType: Option[String] = None,
                             code: Option[String] = None,
                             attributes: Option[Map[String, QName]] = None)

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
                                 attributes: Option[Map[String, QName]] = None)


case class Address(content: Option[String] = None,
                   objectType: Option[String] = None,
                   code: Option[String] = None,
                   attributes: Option[Map[String, QName]] = None) extends AddressDetailsType {

  def this() = this(None, None, None, None)
}

case class CountryNameCode(content: Option[String] = None,
                           scheme: Option[String] = None,
                           code: Option[String] = None,
                           attributes: Option[Map[String, QName]] = None)


case class Country(addressLine: Seq[AddressLine] = Nil,
                   countryNameCode: Seq[CountryNameCode] = Nil,
                   countryName: Seq[Content] = Nil,
                   countryType: Option[CountryType] = None,
                   any: Seq[Any] = Nil,
                   attributes: Option[Map[String, QName]] = None) extends AddressDetailsType {

  def this() = this(Nil, Nil, Nil, None, Nil, None)
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
                          any: Seq[Any] = Nil) {

  def this() = this(None, None, None, None, None, None, None, None, None, None, Nil)
}

trait AddressDetailsType

case class AddressLines(addressLines: Seq[AddressLine] = Nil,
                        any: Seq[Any] = Nil,
                        attributes: Option[Map[String, QName]] = None) extends AddressDetailsType {

  def this() = this(Nil, Nil, None)
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
                        attributes: Option[Map[String, QName]] = None)


case class DependentLocalityNumber(content: Option[String] = None,
                                   nameNumberOccurrence: Option[TypeOccurrence] = None,
                                   code: Option[String] = None,
                                   attributes: Option[Map[String, QName]] = None)


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
                             attributes: Option[Map[String, QName]] = None) extends ThoroughfareType

trait DependentLocalityType

case class Firm(addressLine: Seq[AddressLine] = Nil,
                    firmName: Seq[Content] = Nil,
                    department: Seq[Department] = Nil,
                    mailStop: Option[MailStop] = None,
                    postalCode: Option[PostalCode] = None,
                    any: Seq[Any] = Nil,
                    objectType: Option[String] = None,
                    attributes: Option[Map[String, QName]] = None) extends ThoroughfareType with PremiseType with PremiseType2

case class LargeMailUserIdentifier(content: Option[String] = None,
                                   objectType: Option[String] = None,
                                   indicator: Option[String] = None,
                                   code: Option[String] = None,
                                   attributes: Option[Map[String, QName]] = None)


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
                             attributes: Option[Map[String, QName]] = None) extends DependentLocalityType with LocalityType


case class MailStopNumber(content: Option[String] = None,
                          nameNumberSeparator: Option[String] = None,
                          code: Option[String] = None,
                          attributes: Option[Map[String, QName]] = None)


case class MailStop(addressLine: Seq[AddressLine] = Nil,
                        mailStopName: Option[Content] = None,
                        mailStopNumber: Option[MailStopNumber] = None,
                        any: Seq[Any] = Nil,
                        objectType: Option[String] = None,
                        attributes: Option[Map[String, QName]] = None)


case class PostalRoute(addressLine: Seq[AddressLine] = Nil,
                           postalRouteName: Option[Content] = None,
                           postalRouteNumber: Option[Content] = None,
                           postBox: Option[PostBox] = None,
                           any: Seq[Any] = Nil,
                           objectType: Option[String] = None,
                           attributes: Option[Map[String, QName]] = None) extends DependentLocalityType with LocalityType

case class SubPremiseName(content: Option[String] = None,
                          objectType: Option[String] = None,
                          typeOccurrence: Option[TypeOccurrence] = None,
                          code: Option[String] = None,
                          attributes: Option[Map[String, QName]] = None)


case class SubPremiseLocation(content: Option[String] = None,
                              code: Option[String] = None) extends SubPremiseType

case class SubPremiseNumber(content: Option[String] = None,
                            indicator: Option[String] = None,
                            indicatorOccurrence: Option[TypeOccurrence] = None,
                            numberOccurrence: Option[TypeOccurrence] = None,
                            premiseNumberSeparator: Option[String] = None,
                            objectType: Option[String] = None,
                            code: Option[String] = None,
                            attributes: Option[Map[String, QName]] = None) extends SubPremiseType


case class SubPremiseNumberPrefix(content: Option[String] = None,
                                  numberPrefixSeparator: Option[String] = None,
                                  objectType: Option[String] = None,
                                  code: Option[String] = None,
                                  attributes: Option[Map[String, QName]] = None)


case class SubPremiseNumberSuffix(content: Option[String] = None,
                                  numberSuffixSeparator: Option[String] = None,
                                  objectType: Option[String] = None,
                                  code: Option[String] = None,
                                  attributes: Option[Map[String, QName]] = None)


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
                          attributes: Option[Map[String, QName]] = None) extends PremiseType with PremiseType2

trait SubPremiseType


case class AddressLine(content: Option[String] = None,
                       objectType: Option[String] = None,
                       code: Option[String] = None,
                       attributes: Option[Map[String, QName]] = None)

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

  def this() = this(Nil, Nil, None, None, None, None, None, Nil, None, None, None, None)
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
                                  code: Option[String] = None)

case class ThoroughfareNumberTo(addressLine: Seq[AddressLine] = Nil,
                                  thoroughfareNumberType: Seq[ThoroughfareNumberType] = Nil,
                                  thoroughfareNumberPrefix: Seq[ThoroughfareNumberPrefix] = Nil,
                                  thoroughfareNumberSuffix: Seq[ThoroughfareNumberSuffix] = Nil,
                                  code: Option[String] = None)

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
                                   attributes: Option[Map[String, QName]] = None) extends ThoroughfareNumberType


case class DependentThoroughfare(addressLine: Seq[AddressLine] = Nil,
                                 thoroughfarePreDirection: Option[Content] = None,
                                 thoroughfareLeadingType: Option[Content] = None,
                                 thoroughfareName: Seq[Content] = Nil,
                                 thoroughfareTrailingType: Option[Content] = None,
                                 thoroughfarePostDirection: Option[Content] = None,
                                 any: Seq[Any] = Nil,
                                 objectType: Option[String] = None,
                                 attributes: Option[Map[String, QName]] = None)


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

  def this() = this(Nil, Nil, Nil, Nil, None, None, Nil, None, None, None, None, Nil, None, None, None, None, None, None)
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
                                 attributes: Option[Map[String, QName]] = None)

case class AdministrativeArea(addressLine: Seq[AddressLine] = Nil,
                              administrativeAreaName: Seq[Content] = Nil,
                              subAdministrativeArea: Option[SubAdministrativeArea] = None,
                              administrativeAreaType: Option[AdministrativeAreaType] = None,
                              any: Seq[Any] = Nil,
                              objectType: Option[String] = None,
                              usageType: Option[String] = None,
                              indicator: Option[String] = None,
                              attributes: Option[Map[String, QName]] = None) extends CountryType with AddressDetailsType {

  def this() = this(Nil, Nil, None, None, Nil, None, None, None, None)
}

trait AdministrativeAreaType

case class PostOfficeNumber(content: Option[String] = None,
                            indicator: Option[String] = None,
                            indicatorOccurrence: Option[TypeOccurrence] = None,
                            code: Option[String] = None,
                            attributes: Option[Map[String, QName]] = None)


case class PostOffice(addressLine: Seq[AddressLine] = Nil,
                      postOfficeNumber: Option[PostOfficeNumber] = None,  // was Seq but ref indicates (0 or 1)
                      postOfficeName: Seq[Content] = Nil,
                      postalRoute: Option[PostalRoute] = None,
                      postBox: Option[PostBox] = None,
                      postalCode: Option[PostalCode] = None,
                      any: Seq[Any] = Nil,
                      objectType: Option[String] = None,
                      indicator: Option[String] = None,
                      attributes: Option[Map[String, QName]] = None) extends DependentLocalityType with LocalityType with AdministrativeAreaType

case class PostalCodeNumberExtension(content: Option[String] = None,
                                     objectType: Option[String] = None,
                                     numberExtensionSeparator: Option[String] = None,
                                     code: Option[String] = None,
                                     attributes: Option[Map[String, QName]] = None)

case class PostTownSuffix(content: Option[String] = None,
                          code: Option[String] = None,
                          attributes: Option[Map[String, QName]] = None)


case class PostTown(addressLine: Seq[AddressLine] = Nil,
                    postTownName: Seq[Content] = Nil,
                    postTownSuffix: Option[PostTownSuffix] = None,
                    objectType: Option[String] = None,
                    attributes: Option[Map[String, QName]] = None)


case class PostalCode(addressLine: Seq[AddressLine] = Nil,
                      postalCodeNumber: Seq[Content] = Nil,
                      postalCodeNumberExtension: Seq[PostalCodeNumberExtension] = Nil,
                      postTown: Option[PostTown] = None,
                      any: Seq[Any] = Nil,
                      objectType: Option[String] = None,
                      attributes: Option[Map[String, QName]] = None) extends ThoroughfareType with AdministrativeAreaType


case class PostBoxNumber(content: Option[String] = None,
                         code: Option[String] = None,
                         objectType: Option[String] = None,
                         attributes: Option[Map[String, QName]] = None)


case class PostBoxNumberPrefix(content: Option[String] = None,
                               numberPrefixSeparator: Option[String] = None,
                               code: Option[String] = None,
                               attributes: Option[Map[String, QName]] = None)


case class PostBoxNumberSuffix(content: Option[String] = None,
                               numberSuffixSeparator: Option[String] = None,
                               code: Option[String] = None,
                               attributes: Option[Map[String, QName]] = None)


case class PostBoxNumberExtension(content: Option[String] = None,
                                  numberExtensionSeparator: Option[String] = None,
                                  code: Option[String] = None,
                                  attributes: Option[Map[String, QName]] = None)


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
                   attributes: Option[Map[String, QName]] = None) extends DependentLocalityType with LocalityType


case class Department(addressLine: Seq[AddressLine] = Nil,
                      departmentName: Seq[Content] = Nil,
                      mailStop: Option[MailStop] = None,
                      postalCode: Option[PostalCode] = None,
                      any: Seq[Any] = Nil,
                      objectType: Option[String] = None,
                      attributes: Option[Map[String, QName]] = None)

case class PremiseName(content: Option[String] = None,
                       objectType: Option[String] = None,
                       typeOccurrence: Option[TypeOccurrence] = None,
                       code: Option[String] = None,
                       attributes: Option[Map[String, QName]] = None)


case class PremiseLocation(content: Option[String] = None,
                           code: Option[String] = None,
                           attributes: Option[Map[String, QName]] = None) extends PremiseType

case class PremiseNumberRangeFrom(addressLine: Seq[AddressLine] = Nil,
                                  premiseNumberPrefix: Seq[PremiseNumberPrefix] = Nil,
                                  premiseNumber: Seq[PremiseNumber] = Nil,
                                  premiseNumberSuffix: Seq[PremiseNumberSuffix] = Nil)


case class PremiseNumberRangeTo(addressLine: Seq[AddressLine] = Nil,
                                premiseNumberPrefix: Seq[PremiseNumberPrefix] = Nil,
                                premiseNumber: Seq[PremiseNumber] = Nil,
                                premiseNumberSuffix: Seq[PremiseNumberSuffix] = Nil)


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
                   attributes: Option[Map[String, QName]] = None) extends ThoroughfareType

trait PremiseType
trait PremiseType2
trait PremiseNumberType

/** A-12 where 12 is number and A is prefix and "-" is the separator
  */
case class ThoroughfareNumberPrefix(content: Option[String] = None,
                                    numberPrefixSeparator: Option[String] = None,
                                    objectType: Option[String] = None,
                                    code: Option[String] = None,
                                    attributes: Option[Map[String, QName]] = None)


case class ThoroughfareNumberSuffix(content: Option[String] = None,
                                    numberSuffixSeparator: Option[String] = None,
                                    objectType: Option[String] = None,
                                    code: Option[String] = None,
                                    attributes: Option[Map[String, QName]] = None)

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
                              attributes: Option[Map[String, QName]] = None) extends ThoroughfareNumberType

case class PremiseNumber(content: Option[String] = None,
                         numberType: Option[NumberType] = None,
                         objectType: Option[String] = None,
                         indicator: Option[String] = None,
                         indicatorOccurrence: Option[TypeOccurrence] = None,
                         numberTypeOccurrence: Option[TypeOccurrence] = None,
                         code: Option[String] = None,
                         attributes: Option[Map[String, QName]] = None) extends PremiseNumberType


case class PremiseNumberPrefix(content: Option[String],
                               numberPrefixSeparator: Option[String] = None,
                               objectType: Option[String] = None,
                               code: Option[String] = None,
                               attributes: Option[Map[String, QName]] = None)


case class PremiseNumberSuffix(content: Option[String] = None,
                               numberSuffixSeparator: Option[String] = None,
                               objectType: Option[String] = None,
                               code: Option[String] = None,
                               attributes: Option[Map[String, QName]] = None)


case class GrPostal(code: Option[String] = None)

