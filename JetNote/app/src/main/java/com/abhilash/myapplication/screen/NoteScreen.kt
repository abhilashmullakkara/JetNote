package com.abhilash.myapplication.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhilash.myapplication.R
import com.abhilash.myapplication.components.NoteButton
import com.abhilash.myapplication.components.NoteInputText
import com.abhilash.myapplication.model.Note
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    notes:List<Note>,
    onAddNote:(Note)->Unit,
    onRemoveNote:(Note)->Unit
){
    var title by remember{
        mutableStateOf("")
    }
    var description by remember{
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(6.dp) ) {
        TopAppBar(title={
             Text(text = stringResource(id = R.string.app_name))           
        },
            actions = {
              Icon(imageVector = Icons.Rounded.Notifications,
                  contentDescription ="Icon" )
            },
            backgroundColor = Color(0xFFA7B0E7)
        )
        //Contents
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(
                modifier=Modifier.padding(top=9.dp, bottom =8.dp),
                text =title, label ="Title" ,
                onTextChange ={
                    if (it.all{char->
                            char.isLetter() || char.isWhitespace()
                        })title=it
                } )
            NoteInputText(
                modifier=Modifier.padding(top=9.dp, bottom =8.dp),
                text =description, label ="Add a note" ,
                onTextChange ={
                    if (it.all{char->
                            char.isLetter() || char.isWhitespace()
                        })description=it
                } )
            NoteButton(text = "Save",
                onClick = { if(title.isNotEmpty() && description.isNotEmpty())
                    {
                    title=""
                        description=""
                    }

                })
            NoteButton(text = "Delete", onClick = { /*TODO*/ })

        }
Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(notes){
                notes->
               NotesRow(note = notes, onNoteClicked = {})
            }
        }



    }

 }

@Composable

fun NotesRow(
    modifier:Modifier=Modifier,
    note:Note,
    onNoteClicked:(Note)->Unit){
        Surface(
            modifier
                .padding(6.dp)
                .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
                .fillMaxWidth(),color=Color(0xFFDFE6EB),
        elevation = 6.dp) {
            Column(
                modifier
                    .clickable { }
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.Start) {
                Text(text=note.title, style = MaterialTheme.typography.subtitle2
                )
                Text(text=note.description,style=MaterialTheme.typography.subtitle1
                )
                Text(text=note.entryDate.format(DateTimeFormatter.ofPattern("EEE,d MMM y")),
                    style = MaterialTheme.typography.caption)

            }

        }
    }




@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
   // NoteScreen()
}