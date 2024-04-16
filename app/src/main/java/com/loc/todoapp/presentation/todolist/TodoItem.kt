package com.loc.todoapp.presentation.todolist

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.loc.todoapp.data.local.taskdata.TaskModel
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.TodoItem(
    task: TaskModel,
    onEvent: (TodoListEvent) -> Unit

) {
    val color by animateColorAsState(
        targetValue = if (task.isDone) Color(0xFF24D65F) else Color(0xFFFF6363),
        label = "",
        animationSpec = tween(500)
    )
    Box(
        modifier = Modifier
            .animateItemPlacement()
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(color)
                .clickable { onEvent(TodoListEvent.OnDoneChange(task, !task.isDone)) }
                .padding(
                    horizontal = 8.dp,
                    vertical = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        AnimatedVisibility(
                            visible = task.isDone,
                            enter = scaleIn() + fadeIn(),
                            exit = scaleOut() + fadeOut()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Check",

                                tint = color
                            )
                        }
                    }
                    Row {
                        AnimatedVisibility(
                            visible = !task.isDone,
                            enter = scaleIn() + fadeIn(),
                            exit = scaleOut() + fadeOut()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Check",
                                tint = color
                            )
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment =  Alignment.CenterHorizontally
                ) {
                    Text(
                    text = task.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    )
                    Text(
                        text = task.description,
                        color = Color(0xFFEBEBEB),
                        fontSize = 12.sp
                    )
                }
            }
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ){
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {onEvent(TodoListEvent.OnTaskClick(task))},
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Edit,
                    tint = Color.White,
                    contentDescription = "Edit"
                )
            }
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Delete,
                        tint = Color.White,
                        contentDescription = "Delete",
                        modifier = Modifier
                            .clickable {onEvent(TodoListEvent.OnDeleteTaskClick(task))}
                    )
                }
            }

        }
        Text(
            text = task.startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            color = Color.White,
            fontSize = 10.sp,
            modifier = Modifier
                .padding(start = 8.dp, bottom = 1.dp)
                .align(Alignment.BottomStart)
        )
        task.endDate?.let {
            Text(
                text = it.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                color = Color.White,
                fontSize = 10.sp,
                modifier = Modifier
                    .padding(end = 8.dp, bottom = 1.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}