package com.loc.todoapp.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loc.todoapp.data.model.TodoList
import com.loc.todoapp.data.model.addDate


@Composable
fun TodoItem(
    todo: TodoList,
    onClick: () -> Unit,
    onDelete: () -> Unit
//    onEdit: (TodoList) -> Unit
) {
    val color by animateColorAsState(
        targetValue = if (todo.isDone) Color(0xFF24D65F) else Color(0xFFFF6363),
        label = "",
        animationSpec = tween(500)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(color)
                .clickable { onClick() }
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                                visible = todo.isDone,
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
                                visible = !todo.isDone,
                                enter = scaleIn() + fadeIn(),
                                exit = scaleOut() + fadeOut()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    tint = color
                                )
                            }
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = todo.title,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                        Text(
                            text = todo.subTitle,
                            color = Color(0xFFEBEBEB),
                            fontSize = 12.sp
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.End,
                ) {
                    Box(
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                            .clickable { onDelete() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            tint = Color.White,
                            contentDescription = "Delete"
                        )
                    }
                    Spacer(modifier = Modifier.height(11.dp))
                    Box(
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                            .clickable { onDelete() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Edit,
                            tint = Color.White,
                            contentDescription = "Edit"
                        )
                    }
                }
            }
            Text(
                text = todo.addDate,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(end = 8.dp, bottom = 8.dp)
                    .align(Alignment.Start)
            )
        }
    }

}

@Composable
fun EditScreen(todo: TodoList, onTodoUpdated: (TodoList) -> Unit) {
    var title by remember { mutableStateOf(todo.title) }
    var subTitle by remember { mutableStateOf(todo.subTitle) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Edit Todo", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = subTitle,
            onValueChange = { subTitle = it },
            label = { Text("Subtitle") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            todo.title = title
            todo.subTitle = subTitle

            onTodoUpdated(todo)
        }) {
            Text("Save")
        }
    }
}

@Preview
@Composable
fun TodoItemPreview() {
    EditScreen(todo = TodoList(), onTodoUpdated = {})
}