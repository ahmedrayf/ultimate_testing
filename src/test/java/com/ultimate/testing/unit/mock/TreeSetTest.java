package com.ultimate.testing.unit.mock;

import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.util.Comparator;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class TreeSetTest {


    @Mock
    private Comparator<String> comparatorMock;

    private AutoCloseable closeable;

    @BeforeEach
    public void initializeMocks() {

        closeable = openMocks(this);

        when(comparatorMock.compare("Alice", "Bob")).thenReturn(1);
        when(comparatorMock.compare("Bob", "Charles")).thenReturn(1);
        when(comparatorMock.compare("Alice", "Charles")).thenReturn(1);

        when(comparatorMock.compare("Bob", "Alice")).thenReturn(-1);
        when(comparatorMock.compare("Charles", "Bob")).thenReturn(-1);
        when(comparatorMock.compare("Charles", "Alice")).thenReturn(-1);

        when(comparatorMock.compare("Alice", "Alice")).thenReturn(0);
        when(comparatorMock.compare("Bob", "Bob")).thenReturn(0);
        when(comparatorMock.compare("Charles", "Charles")).thenReturn(0);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void testTreeSet() {
        TreeSet<String> treeSet = new TreeSet<>(comparatorMock);

        treeSet.add("Bob");
        treeSet.add("Alice");
        treeSet.add("Charles");

        assertEquals("Charles", treeSet.first());
        assertEquals("Alice", treeSet.last());

        assertEquals("Bob", treeSet.higher("Charles"));
        assertEquals("Bob", treeSet.lower("Alice"));
    }
}
