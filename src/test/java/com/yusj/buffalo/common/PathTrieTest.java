package com.yusj.buffalo.common;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathTrieTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathTrieTest.class);

    @Test
    public void testPathTrieRoot() {
        PathTrie pathTrie = new PathTrie();
        pathTrie.addPath("/a/b/c/d");
        pathTrie.addPath("/a/b/e/f");
        pathTrie.deletePath("/a/b/c");
    }

}
