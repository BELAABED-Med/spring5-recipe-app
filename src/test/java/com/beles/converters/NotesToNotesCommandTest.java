package com.beles.converters;

import com.beles.commands.NotesCommand;
import com.beles.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    private static final Long ID=1L;
    private static final String NOTES="Some notes";

    NotesToNotesCommand notesToNotesCommand;



    @BeforeEach
    void setUp() {
        notesToNotesCommand=new NotesToNotesCommand();
    }

    @Test
    void convertTestNull() {
        assertNull(notesToNotesCommand.convert(null));
    }

    @Test
    void convertTestEmpty() {
        assertNotNull(notesToNotesCommand.convert(new Notes()));
    }

    @Test
    void convert() {
        //Given
        Notes notes=new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(NOTES);

        //When
        NotesCommand notesCommand=notesToNotesCommand.convert(notes);

        //Then
        assertNotNull(notesCommand);
        assertNotNull(notes);
        assertEquals(ID,notesCommand.getId());
        assertEquals(NOTES,notesCommand.getRecipeNotes());

    }
}
