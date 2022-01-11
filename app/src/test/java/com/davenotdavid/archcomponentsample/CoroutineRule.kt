package com.davenotdavid.archcomponentsample

import kotlinx.coroutines.Dispatchers
import org.junit.rules.TestWatcher
import org.junit.runner.Description

// TODO: Ref: https://stackoverflow.com/a/65743122/5385758

//class CoroutineRule(
//    val testCoroutineDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
//) : TestWatcher(),
//    TestCoroutineScope by TestCoroutineScope(testCoroutineDispatcher) {
//
//    override fun starting(description: Description?) {
//        super.starting(description)
//        Dispatchers.setMain(testCoroutineDispatcher)
//    }
//
//    override fun finished(description: Description?) {
//        super.finished(description)
//        Dispatchers.resetMain()
//        testCoroutineDispatcher.cleanupTestCoroutines()
//    }
//
//    /**
//     * Convenience method for calling [runBlockingTest] on a provided [TestCoroutineDispatcher].
//     */
//    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) {
//        testCoroutineDispatcher.runBlockingTest(block)
//    }
//}
