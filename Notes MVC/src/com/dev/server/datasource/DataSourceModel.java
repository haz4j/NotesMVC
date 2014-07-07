package com.dev.server.datasource;

import java.util.ArrayList;

import com.dev.note.Note;
import com.dev.note.NoteModel;

/**
 * The Interface DataSourceModel.
 */
public interface DataSourceModel {

	/**
	 * Gets the stored notes.
	 *
	 * @return the stored notes
	 */
	ArrayList<NoteModel> getStoredNotes();

	/**
	 * Sets the notes.
	 *
	 * @param storedNotes
	 *            the new notes
	 */
	void setNotes(ArrayList<NoteModel> storedNotes);

	/**
	 * Removes the note.
	 *
	 * @param noteId
	 *            the note id
	 */
	void removeNote(Integer noteId);

	/**
	 * Gets the note.
	 *
	 * @param noteId
	 *            the note id
	 * @return the note
	 */
	NoteModel getNote(Integer noteId);

	/**
	 * Adds the note.
	 *
	 * @param note
	 *            the note
	 */
	void addNote(Note note);

	/**
	 * Adds the note.
	 *
	 * @param text
	 *            the text
	 */
	void addNote(String text);

	/**
	 * Update note.
	 *
	 * @param integer
	 *            the integer
	 * @param text
	 *            the text
	 */
	void updateNote(Integer integer, String text);
}
