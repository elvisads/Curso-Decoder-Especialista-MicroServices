package com.ead.course.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.ModuleService;

import jakarta.transaction.Transactional;

@Service
public class ModuleServieImpl implements ModuleService {

	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	LessonRepository lessonRepository;

	@Transactional
	@Override
	public void delete(ModuleModel moduleModel) {
		List<LessonModel> lessonModelList = lessonRepository.findAllLessonsIntoModule(moduleModel.getModuleId());
		if (!lessonModelList.isEmpty()) {
			lessonRepository.deleteAll(lessonModelList);
		}
		moduleRepository.delete(moduleModel);
	}
}
