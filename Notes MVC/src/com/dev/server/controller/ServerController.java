package com.dev.server.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.dev.note.Note;
import com.dev.server.datasource.DataSourceModel;

/**
 * The Class ServerController for Spring MVC
 */
@Controller
public class ServerController {

	/** The data source model. */
	private DataSourceModel dataSourceModel;

	/**
	 * Instantiates a new server controller.
	 *
	 * @param dataSourceModel
	 *            the data source model
	 */
	@Autowired
	public ServerController(DataSourceModel dataSourceModel) {
		this.dataSourceModel = dataSourceModel;
	}

	/**
	 * Method show notes fires on main page. It just places all notes on page.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping("/")
	public String showNotes(Map<String, Object> model) {
		model.put("notes", dataSourceModel.getStoredNotes());
		return "main-page";
	}

	/**
	 * Initiates the "add note" form.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String initForm(Map<String, Object> model) {
		Note note = new Note();
		model.put("note", note);
		return "add-page";
	}

	/**
	 * If user clicked on "OK" button on /add page we submit new entry to
	 * dataSourceModel
	 *
	 * @param text
	 *            the text
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @return the string
	 */
	@RequestMapping(value = "/added", method = RequestMethod.POST, params = { "OK" })
	public String processSubmitOK(@ModelAttribute("text") String text, BindingResult result, SessionStatus status) {
		dataSourceModel.addNote(text);
		return "redirect:/";
	}

	/**
	 * If user clicked on "Cancel" button on /add page we redirect to main page
	 *
	 * @param text
	 *            the text
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @return the string
	 */
	@RequestMapping(value = "/added", method = RequestMethod.POST, params = { "Cancel" })
	public String processSubmitCancel(@ModelAttribute("text") String text, BindingResult result, SessionStatus status) {
		return "redirect:/";
	}

	/**
	 * Initiates the "edit note" form.
	 *
	 * @param noteId
	 *            the note id
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/edit/{noteId}", method = RequestMethod.GET)
	public String initEdit(@PathVariable String noteId, Map<String, Object> model) {

		Note note = (Note) dataSourceModel.getNote(Integer.parseInt(noteId));
		model.put("note", note);
		return "edit-page";
	}

	/**
	 * If user clicked on "OK" button on /edit page we submit edited entry to
	 * dataSourceModel
	 *
	 * @param noteId
	 *            the note id
	 * @param note
	 *            the note
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @return the string
	 */
	@RequestMapping(value = "/edited/{noteId}", method = RequestMethod.POST, params = { "OK" })
	public String processEditOK(@PathVariable String noteId, @ModelAttribute("note") Note note, BindingResult result,
			SessionStatus status) {
		dataSourceModel.updateNote(Integer.parseInt(noteId), note.getText());
		return "redirect:/";
	}

	/**
	 * If user clicked on "Cancel" button on /edit page we redirect to main page
	 *
	 * @param noteId
	 *            the note id
	 * @param note
	 *            the note
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @return the string
	 */
	@RequestMapping(value = "/edited/{noteId}", method = RequestMethod.POST, params = { "Cancel" })
	public String processEditCancel(@PathVariable String noteId, @ModelAttribute("note") Note note,
			BindingResult result, SessionStatus status) {
		return "redirect:/";
	}

	/**
	 * Inits the delete.
	 *
	 * @param noteId
	 *            the note id
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/delete/{noteId}", method = RequestMethod.GET)
	public String initDelete(@PathVariable String noteId, Map<String, Object> model) {
		Note note = (Note) dataSourceModel.getNote(Integer.parseInt(noteId));
		model.put("note", note);
		return "delete-page";
	}

	/**
	 * If user clicked on "OK" button on /edit page we delete entry from
	 * dataSourceModel
	 *
	 * @param noteId
	 *            the note id
	 * @param note
	 *            the note
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @return the string
	 */
	@RequestMapping(value = "/deleted/{noteId}", method = RequestMethod.POST, params = { "OK" })
	public String processDeleteOK(@PathVariable String noteId, @ModelAttribute("note") Note note, BindingResult result,
			SessionStatus status) {
		dataSourceModel.removeNote(Integer.parseInt(noteId));
		return "redirect:/";
	}

	/**
	 * If user clicked on "Cancel" button on /edit page we redirect to main page
	 *
	 * @param noteId
	 *            the note id
	 * @param note
	 *            the note
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @return the string
	 */
	@RequestMapping(value = "/deleted/{noteId}", method = RequestMethod.POST, params = { "Cancel" })
	public String processDeleteCancel(@PathVariable String noteId, @ModelAttribute("note") Note note,
			BindingResult result, SessionStatus status) {
		return "redirect:/";
	}
}