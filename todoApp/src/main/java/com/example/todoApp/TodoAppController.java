package com.example.todoApp;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.todoApp.repositories.TodoDataRepository;

@Controller
public class TodoAppController {
	
	@Autowired
	TodoDataRepository repository;
	
	@PostConstruct
	public void init() {
		TodoData d1 = new TodoData();
		d1.setTitle("レポートサマリーを作る");
		d1.setCreateDate("2019/08/05");
		d1.setDeadline("2019/08/10");
		d1.setDone(false);
		repository.saveAndFlush(d1);

		TodoData d2 = new TodoData();
		d2.setTitle("レポート表紙を作る");
		d2.setCreateDate("2019/08/08");
		d2.setDeadline("2019/08/18");
		d2.setDone(false);
		repository.saveAndFlush(d2);
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(
			@ModelAttribute("formModel") TodoData tododata,
			ModelAndView mav) {
		mav.setViewName("index");
		mav.addObject("msg", "this is sampl content");
		mav.addObject("doneTxt", "完了");
		mav.addObject("undoneTxt", "未完了");
		mav.addObject("formModel", tododata);
		Iterable<TodoData> list = repository.findAll();
		mav.addObject("datalist", list);
		return mav;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView form(
			@ModelAttribute("formModel")
			@Validated TodoData tododata,
			BindingResult result,
			ModelAndView mov) {
		ModelAndView res = null;
		if (!result.hasErrors()) {
			repository.saveAndFlush(tododata);
			res = new ModelAndView("redirect:/");
		} else {
			mov.setViewName("index");
			mov.addObject("msg", "sorry, error is occured...");
			mov.addObject("doneTxt", "完了");
			mov.addObject("undoneTxt", "未完了");
			Iterable<TodoData> list = repository.findAll();
			mov.addObject("datalist", list);
			res = mov;
		}
		return res;
	}

	@RequestMapping(value="/done/{id}", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView checkDone(@ModelAttribute TodoData tododata,
			@PathVariable int id, ModelAndView mav) {
		Optional<TodoData> data = repository.findById((long)id);
		boolean tododataDone = data.get().getDone();
		data.get().setDone(!tododataDone);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute TodoData tododata,
			@PathVariable int id, ModelAndView mav) {
		mav.setViewName("edit");
		mav.addObject("title", "edit todoData");
		Optional<TodoData> data = repository.findById((long)id);
		mav.addObject("formModel", data.get());
		return mav;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView update(
			@ModelAttribute("formModel")
			@Validated TodoData tododata,
			BindingResult result,
			ModelAndView mov) {
		ModelAndView res = null;
		if (!result.hasErrors()) {
			repository.saveAndFlush(tododata);
			res = new ModelAndView("redirect:/");
		} else {
			mov.setViewName("edit");
			mov.addObject("formModel", tododata);
			res = mov;
		}
		return res;
	}
	
	@RequestMapping(value="/searchpage", method=RequestMethod.GET)
	public ModelAndView searchPage(ModelAndView mav) {
		mav.setViewName("searchpage");
		return mav;
	}

	@RequestMapping(value="/search", method=RequestMethod.POST)
	public ModelAndView search(
			@RequestParam("searchWord")String str,
			ModelAndView mav) {
		mav.setViewName("searchpage");
		String msg = "対象のToDoは見つかりません";

		mav.addObject("msg", msg);
		return mav;
	}
}
