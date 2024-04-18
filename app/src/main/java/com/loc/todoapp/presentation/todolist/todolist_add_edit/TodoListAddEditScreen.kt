package com.loc.todoapp.presentation.todolist.todolist_add_edit

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loc.todoapp.presentation.theme.ComposeDateTimePickerTheme
import com.loc.todoapp.util.UIEvent
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoListAddEditScreen(
    onPopBackStack: () -> Unit,
    viewModel: TodoListAddEditViewModel = hiltViewModel()
) {
    ComposeDateTimePickerTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        LaunchedEffect(key1 = true) {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is UIEvent.PopBackStack -> onPopBackStack()
                    is UIEvent.ShowSnackbar -> {
                        snackbarHostState.showSnackbar(
                            message = event.message,
                            actionLabel = event.actionLabel
                        )
                    }
                    else -> Unit
                }
            }
        }

        val formattedDate by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("dd/MM/yyyy")
                    .format(viewModel.startDate)
            }
        }
        val formattedEndDate by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("dd/MM/yyyy")
                    .format(viewModel.endDate)
            }
        }
        val formattedTime by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("HH:mm")
                    .format(viewModel.startTime)
            }
        }
        val formattedEndTime by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("HH:mm")
                    .format(viewModel.endTime)
            }
        }
        val dateDialogState = rememberMaterialDialogState()
        val dateEndDialogState = rememberMaterialDialogState()
        val timeDialogState = rememberMaterialDialogState()
        val timeEndDialogState = rememberMaterialDialogState()
        val context = LocalContext.current

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            floatingActionButton = {
                FloatingActionButton(onClick = {
                        viewModel.onEvent(TodoListAddEditEvent.OnSaveTodoClick)
                }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Đã lưu"
                    )
                }
            }
        ) {
            Column(

                modifier = Modifier.fillMaxSize()

            ) {

                TextField(

                    value = viewModel.title,

                    onValueChange = {

                        viewModel.onEvent(TodoListAddEditEvent.OnTitleChange(it))

                    },

                    placeholder = {

                        Text(text = "Tiêu đề")

                    },

                    modifier = Modifier

                        .fillMaxWidth()

                        .padding(8.dp)

                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))

                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(

                    value = viewModel.description,

                    onValueChange = {

                        viewModel.onEvent(TodoListAddEditEvent.OnDescriptionChange(it))

                    },

                    placeholder = {

                        Text(text = "Nội dung")

                    },

                    modifier = Modifier

                        .fillMaxWidth()

                        .padding(8.dp)

                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),

                    singleLine = false,

                    maxLines = 5

                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceBetween,


                    ) {

                    Column(

                        modifier = Modifier

                            .weight(1f)

                            .wrapContentHeight(),

                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Button(

                            modifier = Modifier

                                .wrapContentHeight()

                                .wrapContentWidth(),

                            shape = RoundedCornerShape(8.dp),

                            onClick = {

                                dateDialogState.show()

                            },

                            ) {

                            Icon(

                                imageVector = Icons.Default.DateRange,

                                contentDescription = "Lịch"

                            )

                            Text(text = "Ngày bắt đầu: $formattedDate")

                        }

                        Button(

                            modifier = Modifier
                                .wrapContentHeight(),

                            shape = RoundedCornerShape(8.dp),

                            onClick = {

                                timeDialogState.show()

                            }

                        ) {

                            Text(text = "Giờ bắt đầu: $formattedTime")

                        }

                    }

                    Column(

                        modifier = Modifier

                            .weight(1f)

                            .wrapContentHeight(),

                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {

                        Button(

                            modifier = Modifier

                                .wrapContentHeight(),

                            shape = RoundedCornerShape(8.dp),

                            onClick = {

                                dateEndDialogState.show()

                            }

                        ) {

                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Lịch"
                            )

                            Text(text = "Ngày kết thúc: $formattedEndDate")

                        }

                        Button(

                            modifier = Modifier

                                .wrapContentHeight(),

                            shape = RoundedCornerShape(8.dp),

                            onClick = {

                                timeEndDialogState.show()

                            }

                        ) {

                            Text(text = "Giờ kết thúc: $formattedEndTime")

                        }

                    }
                }
                MaterialDialog(
                    dialogState = dateDialogState,
                    buttons = {
                        positiveButton(text = "Hoàn tất") {
                            Toast.makeText(
                                context,
                                "Thêm ngày bắt đầu thành công",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        negativeButton(text = "Hủy")
                    }
                ) {
                    datepicker(
                        initialDate = viewModel.startDate,
                        title = "Chọn ngày",
                    ) { selectedDate ->
                        viewModel.onEvent(TodoListAddEditEvent.OnStartDateChange(selectedDate))
                    }
                }
                MaterialDialog(
                    dialogState = timeDialogState,
                    buttons = {
                        positiveButton(text = "Hoàn tất") {
                            Toast.makeText(
                                context,
                                "Thêm giờ bắt đầu thành công",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        negativeButton(text = "Hủy")
                    }
                ) {
                    timepicker(
                        initialTime = viewModel.startTime,
                        title = "Chọn giờ",
                        timeRange = LocalTime.MIDNIGHT..LocalTime.MAX,
                        is24HourClock = true
                    ) {selectedTime ->
                        viewModel.onEvent(TodoListAddEditEvent.OnStartTimeChange(selectedTime))
                    }
                }
                MaterialDialog(
                    dialogState = dateEndDialogState,
                    buttons = {
                        positiveButton(text = "Hoàn tất") {
                            Toast.makeText(
                                context,
                                "Thêm ngày kết thúc thành công",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        negativeButton(text = "Hủy")
                    }
                ) {
                    datepicker(
                        initialDate = viewModel.endDate,
                        title = "Chọn ngày kết thúc",
                        allowedDateValidator = {
                            it.isAfter(viewModel.startDate)
                        }
                    ) { selectedDate ->
                        viewModel.onEvent(TodoListAddEditEvent.OnEndDateChange(selectedDate))
                    }
                }
                MaterialDialog(
                    dialogState = timeEndDialogState,
                    buttons = {
                        positiveButton(text = "Hoàn tất") {
                            Toast.makeText(
                                context,
                                "Thêm giờ kết thúc thành công",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        negativeButton(text = "Hủy")
                    }
                ) {
                    timepicker(
                        initialTime = viewModel.endTime,
                        title = "Chọn giờ kết thúc",
                        timeRange = LocalTime.MIDNIGHT..LocalTime.MAX,
                        is24HourClock = true
                    ) {selectedTime ->
                        viewModel.onEvent(TodoListAddEditEvent.OnEndTimeChange(selectedTime))
                    }
                }
            }
        }
    }
}

