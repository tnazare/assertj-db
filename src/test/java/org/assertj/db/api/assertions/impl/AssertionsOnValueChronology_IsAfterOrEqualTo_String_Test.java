/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.impl.AssertionsOnValueChronology} class :
 * {@link org.assertj.db.api.assertions.impl.AssertionsOnValueChronology#isAfterOrEqualTo(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, Object, org.assertj.db.type.DateTimeValue)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnValueChronology_IsAfterOrEqualTo_String_Test {

  /**
   * This method tests the {@code isAfterOrEqualTo} assertion method.
   */
  @Test
  public void test_is_after_or_equal_to() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    TableAssert tableAssert2 = AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                                            Timestamp.valueOf("2007-12-23 09:01:05"),
                                                                            "2007-12-23T09:01:05");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                                Timestamp.valueOf("2007-12-23 09:01:05"),
                                                                "2007-12-23T09:01:00");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                                Date.valueOf("2007-12-23"),
                                                                "2007-12-23T00:00:00");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                                Date.valueOf("2007-12-24"),
                                                                "2007-12-23T09:01:05");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                                Timestamp.valueOf("2007-12-23 00:00:00"),
                                                                "2007-12-23");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                                Timestamp.valueOf("2007-12-23 09:01:05"),
                                                                "2007-12-23");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                                Date.valueOf("2007-12-23"),
                                                                "2007-12-23");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                                Date.valueOf("2007-12-24"),
                                                                "2007-12-23");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                                Time.valueOf("09:01:05"),
                                                                "09:01:05");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
    tableAssert2 = AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                                Time.valueOf("09:01:05"),
                                                                "09:01:00");
    Assertions.assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the value is before.
   */
  @Test
  public void should_fail_because_value_is_before() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                   Timestamp.valueOf("2007-12-23 09:01:05"),
                                                   "2007-12-23T09:01:06");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <2007-12-23T09:01:05.000000000>\n"
                                                      + "to be after or equal to \n"
                                                      + "  <2007-12-23T09:01:06.000000000>");
    }
    try {
      AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                           Date.valueOf("2007-12-23"),
                                           "2007-12-23T09:01:05");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <2007-12-23T00:00:00.000000000>\n"
                                                      + "to be after or equal to \n"
                                                      + "  <2007-12-23T09:01:05.000000000>");
    }
    try {
      AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                   Timestamp.valueOf("2007-12-23 09:01:05"),
                                                   "2007-12-24");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <2007-12-23T09:01:05.000000000>\n"
                                                      + "to be after or equal to \n"
                                                      + "  <2007-12-24T00:00:00.000000000>");
    }
    try {
      AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                   Date.valueOf("2007-12-23"),
                                                   "2007-12-24");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <2007-12-23T00:00:00.000000000>\n"
                                                      + "to be after or equal to \n"
                                                      + "  <2007-12-24T00:00:00.000000000>");
    }
    try {
      AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                   Time.valueOf("09:01:05"),
                                                   "09:01:06");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <09:01:05.000000000>\n"
                                                      + "to be after or equal to \n"
                                                      + "  <09:01:06.000000000>");
    }
  }

  /**
   * This method should fail because the value is not compatible.
   */
  @Test
  public void should_fail_because_value_is_not_compatible() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                   "test",
                                                   "2007-12-23T09:01:05");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[description] \n"
                                                      + "Expecting:\n"
                                                      + "  <\"test\">\n"
                                                      + "to be of type\n"
                                                      + "  <[DATE, TIME, DATE_TIME]>\n"
                                                      + "but was of type\n"
                                                      + "  <TEXT>");
    }
  }

  /**
   * This method should fail because the expected string is not correct to compare to a time.
   */
  @Test
  public void should_fail_because_expected_string_is_not_correct_to_compare_to_time() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                   Time.valueOf("09:01:05"),
                                                   "09_01:00");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Expected <09_01:00> is not correct to compare to <09:01:05.000000000>");
    }
  }

  /**
   * This method should fail because the expected string is not correct to compare to.
   */
  @Test
  public void should_fail_because_expected_string_is_not_correct_to_compare_to() {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    try {
      AssertionsOnValueChronology.isAfterOrEqualTo(tableAssert, info,
                                                   Date.valueOf("2007-12-23"),
                                                   "2007_12-23T09:01:00");
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Expected <2007_12-23T09:01:00> is not correct to compare to <2007-12-23T00:00:00.000000000>");
    }
  }
}
