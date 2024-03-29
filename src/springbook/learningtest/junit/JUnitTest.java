package springbook.learningtest.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="junit.xml")
public class JUnitTest {
    @Autowired
    ApplicationContext context;
    static Set<JUnitTest> testObjs = new HashSet<JUnitTest>();
    static ApplicationContext contextObj = null;

    @Test
    public void test1() {
        assertThat(this, is(not(sameInstance(hasItem(this)))));
        testObjs.add(this);

        assertThat(contextObj == null || contextObj == this.context, is(true));
        contextObj = this.context;
    }

    @Test
    public void test2() {
        assertThat(this, is(not(sameInstance(hasItem(this)))));
        testObjs.add(this);

        assertThat(contextObj == null || contextObj == this.context, is(true));
        contextObj = this.context;
    }

    @Test
    public void test3() {
        assertThat(this, is(not(sameInstance(hasItem(this)))));
        testObjs.add(this);

        assertThat(contextObj == null || contextObj == this.context, is(true));
        contextObj = this.context;
    }
}
