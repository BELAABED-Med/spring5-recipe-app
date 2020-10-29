package com.beles.converters;

import com.beles.commands.NotesCommand;
import com.beles.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    private static final Long ID=1L;
    private static final String NOTES="Some notes";

    NotesCommandToNotes notesCommandToNotes;



    @BeforeEach
    void setUp() {
        notesCommandToNotes=new NotesCommandToNotes();
    }

    @Test
    void convertTestNull() {
        assertNull(notesCommandToNotes.convert(null));
    }

    @Test
    void convertTestEmpty() {
        assertNotNull(notesCommandToNotes.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        //Given
        NotesCommand notesCommand=new NotesCommand();
        notesCommand.setId(ID);
        notesCommand.setRecipeNotes(NOTES);

        //When
        Notes notes=notesCommandToNotes.convert(notesCommand);

        //Then
        assertNotNull(notesCommand);
        assertNotNull(notes);
        assertEquals(ID,notes.getId());
        assertEquals(NOTES,notes.getRecipeNotes());

    }
}
