package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

import java.lang.reflect.Field;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Request;
import org.junit.Test;

/**
 * Test on the {@code getColumn} methods for assertion on {@code Request}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestAssert_GetColumn_Test extends AbstractTest {

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is less than the
   * minimum.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Index -1 out of the limits [0, 4[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_index_is_less_than_the_minimum() {
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    assertThat(request).getColumn(-1);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is more than the
   * maximum.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Index 4 out of the limits [0, 4[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_index_is_more_than_the_maximum() {
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    assertThat(request).getColumn(4);
  }

  /**
   * This method tests the {@code getColumn} method when using with {@code index} parameter.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_when_get_rows_with_index_parameter() throws IllegalArgumentException, IllegalAccessException,
      SecurityException, NoSuchFieldException {

    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    RequestAssert assertion = assertThat(request);

    Field field = AbstractDbAssert.class.getDeclaredField("indexNextColumn");
    field.setAccessible(true);

    assertThat(field.getInt(assertion)).isEqualTo(0);
    assertThat(assertion.getColumn(3)).isSameAs(request.getColumn(3));
    assertThat(field.getInt(assertion)).isEqualTo(4);
    assertThat(assertion.getColumn(2)).isSameAs(request.getColumn(2));
    assertThat(field.getInt(assertion)).isEqualTo(3);
    assertThat(assertion.getColumn(1)).isSameAs(request.getColumn(1));
    assertThat(field.getInt(assertion)).isEqualTo(2);
    assertThat(assertion.getColumn(0)).isSameAs(request.getColumn(0));
    assertThat(field.getInt(assertion)).isEqualTo(1);
  }

}
