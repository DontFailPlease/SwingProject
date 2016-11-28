package data

import org.junit.Test

/**
 * Created by di on 27.11.16.
 */
class DataGeneratorTest extends groovy.util.GroovyTestCase {
    void setUp() {
        super.setUp()

    }

    void tearDown() {

    }

    @Test
    void testGenerateData(Object gui) {
        assertNull("Gui object is NULL.", gui);
        assertNotNull("Gui object is NULL.", gui);
    }

    void testGetData() {
        System.out.println("b");
    }

    void testSortData() {

    }

    void testGetDataRowsCount() {

    }

    void testGetValueByRowNumber() {

    }

    void testSetValueByRowNumber() {

    }
}
