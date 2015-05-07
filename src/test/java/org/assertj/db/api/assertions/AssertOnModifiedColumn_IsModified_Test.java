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
package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnModifiedColumn} class :
 * {@link org.assertj.db.api.assertions.AssertOnModifiedColumn#isModified()} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnModifiedColumn_IsModified_Test extends AbstractTest {

  /**
   * This method tests the {@code isModified} assertion method.
   */
  @Test
  @NeedReload
  public void test_is_modified() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeColumnAssert changeColumnAssert = assertThat(changes).change().column();
    ChangeColumnAssert changeColumnAssert2 = changeColumnAssert.isModified();
    Assertions.assertThat(changeColumnAssert).isSameAs(changeColumnAssert2);
  }

  /**
   * This method should fail because the column is not modified.
   */
  @Test
  @NeedReload
  public void should_fail_because_column_is_not_modified() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    try {
      assertThat(changes).change(3).column().isModified();
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 0 (column name : ID) of Change at index 3 (on table : ACTOR and with primary key : [1]) of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                                      + "Expecting :\n"
                                                      + "  <1>\n"
                                                      + "is modified but is still:\n"
                                                      + "  <1>");
    }
  }
}
