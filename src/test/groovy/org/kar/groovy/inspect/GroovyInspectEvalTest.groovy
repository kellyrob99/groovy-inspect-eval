package org.kar.groovy.inspect

import org.apache.commons.lang.RandomStringUtils
import org.junit.Test
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.equalTo

/**
 * @author Kelly Robinson
 */
class GroovyInspectEvalTest
{
    private static final Closure RANDOM_STRING = RandomStringUtils.&randomAlphanumeric
    private static final String TMP_DIR = System.getProperty('java.io.tmpdir')

    @Test
    public void testSerializeListToFile()
    {
        List<String> accumulator = []
        100.times { int i ->
            accumulator << RANDOM_STRING(i + 1)
        }
        File file = new File("$TMP_DIR/inspectListTest.groovy")
        file.deleteOnExit()
        file << accumulator.inspect()
        assertThat(accumulator, equalTo(Eval.me(file.text)))
    }

    @Test
    public void testSerializeMapToFile()
    {
        Map<String, String> accumulator = [:]
        100.times { int i ->
            accumulator[RANDOM_STRING(i + 1)] = RANDOM_STRING(i + 1)
        }
        File file = new File("$TMP_DIR/inspectMapTest.groovy")
        file.deleteOnExit()
        file << accumulator.inspect()
        assertThat(accumulator, equalTo(Eval.me(file.text)))
    }
}
