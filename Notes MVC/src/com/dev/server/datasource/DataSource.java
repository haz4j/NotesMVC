package com.dev.server.datasource;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dev.note.Note;
import com.dev.note.NoteModel;

/**
 * The Class DataSource.
 */
@Component
public class DataSource implements DataSourceModel {

	/** The session factory. */
	@Autowired
	public SessionFactory sessionFactory;

	/** The notes. */
	public ArrayList<NoteModel> notes;

	/** The session. */
	public Session session = null;

	/**
	 * Instantiates a new data source.
	 */
	public DataSource() {
	}

	/*
	 * @see com.dev.server.datasource.DataSourceModel#getStoredNotes()
	 */
	public ArrayList<NoteModel> getStoredNotes() {
		session = sessionFactory.openSession();
		session.beginTransaction();
		notes = (ArrayList<NoteModel>) session.createCriteria(NoteModel.class).list();
		session.getTransaction().commit();
		session.close();
		return notes;
	}

	/*
	 * @see
	 * com.dev.server.datasource.DataSourceModel#setNotes(java.util.ArrayList)
	 */
	@Transactional
	public void setNotes(ArrayList<NoteModel> notes) {
		session = sessionFactory.openSession();
		session.beginTransaction();

		for (NoteModel noteModel : notes) {
			Note serverNote = (Note) session.get(Note.class, noteModel.getId());
			if (serverNote == null) {
				session.save(noteModel);
			} else if (!serverNote.equals(noteModel)) {
				session.update(noteModel);
			}
		}
		session.getTransaction().commit();
		session.close();
	}

	/*
	 * @see
	 * com.dev.server.datasource.DataSourceModel#removeNote(java.lang.Integer)
	 */
	@Transactional
	public void removeNote(Integer noteId) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Note note = (Note) session.get(Note.class, noteId);

		if (note != null) {
			session.delete(note);
		}

		session.getTransaction().commit();
		session.close();
	}

	/*
	 * @see com.dev.server.datasource.DataSourceModel#getNote(java.lang.Integer)
	 */
	@Transactional
	public NoteModel getNote(Integer noteId) {

		session = sessionFactory.openSession();
		session.beginTransaction();
		Note note = (Note) session.get(Note.class, noteId);
		session.getTransaction().commit();
		session.close();
		return note;
	}

	/*
	 * @see com.dev.server.datasource.DataSourceModel#addNote(com.dev.note.Note)
	 */
	@Transactional
	public void addNote(Note note) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(note);
		session.getTransaction().commit();
		session.close();
	}

	/*
	 * @see com.dev.server.datasource.DataSourceModel#addNote(java.lang.String)
	 */
	@Transactional
	public void addNote(String text) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Note note = new Note();
		note.setChanged(true);
		note.setDeleted(false);
		note.setText(text);
		session.save(note);
		session.getTransaction().commit();
		session.close();
	}

	/*
	 * @see
	 * com.dev.server.datasource.DataSourceModel#updateNote(java.lang.Integer,
	 * java.lang.String)
	 */
	@Transactional
	public void updateNote(Integer noteId, String text) {
		session = sessionFactory.openSession();
		session.beginTransaction();
		Note note = (Note) session.get(Note.class, noteId);
		note.setText(text);
		note.setChanged(true);
		session.getTransaction().commit();
		session.close();
	}
}
