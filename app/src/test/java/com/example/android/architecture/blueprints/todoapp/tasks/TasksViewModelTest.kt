package com.example.android.architecture.blueprints.todoapp.tasks

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

// *** Run with COVERAGE will not work ***
@RunWith(AndroidJUnit4::class) // need this because error msg: No instrumentation registered!
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class TasksViewModelTest {
    // subject under test
    private lateinit var tasksViewModel: TasksViewModel // don't init here, it would not be fresh

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        // Given
        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
    }
    // Test fun naming covention when<thisHappens>_then<expectedResult>
    @Test
    fun addNewTask_setNewTaskEvent() {


        // When
        tasksViewModel.addNewTask()
        // Then
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(), not(nullValue()))
    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {

        // when
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)
        // then
        // assertThat(tasksViewModel.tasksAddViewVisible.value, `is`(true))
        // ***** Use getOrAwaitValue() when LiveData *****
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(true))
    }

    @Test
    fun setCompletedTasks_tasksAddViewNotVisible() {

        tasksViewModel.setFiltering(TasksFilterType.COMPLETED_TASKS)
        // then
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(false))
    }
}