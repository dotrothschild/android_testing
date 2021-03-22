package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test


class StatisticsUtilsTest {


    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        // create an active task
        val tasks = listOf<Task>(
                Task("title", "desc", isCompleted = false)
        )
        // call function under test
        val result = getActiveAndCompletedStats(tasks)
        // assert
        assertThat(result.activeTasksPercent, `is`(100f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getStats_noTasks() {

        val tasks = listOf<Task>()
        val result = getActiveAndCompletedStats(tasks)
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getStats_nullList() {
        var result = getActiveAndCompletedStats(null)
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getStats_oneComplete() {
        val tasks = listOf<Task>(
                Task("title", "desc", isCompleted = true)
        )
        // call function under test
        val result = getActiveAndCompletedStats(tasks)
        // assert
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(100f))
    }

    @Test
    fun getStats_SomeComplete() {
        val tasks = listOf<Task>(
                Task("title", "desc", isCompleted = false),
                Task("title2", "desc2", isCompleted = true),
                Task("title3", "desc3", isCompleted = false),
                Task("title4", "desc4", isCompleted = false),
                Task("title5", "desc5", isCompleted = true)
        )
        // call function under test
        val result = getActiveAndCompletedStats(tasks)
        // assert
        assertThat(result.activeTasksPercent, `is`(60f))
        assertThat(result.completedTasksPercent, `is`(40f))
    }
}