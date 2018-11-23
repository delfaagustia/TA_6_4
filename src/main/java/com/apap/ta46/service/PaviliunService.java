package com.apap.ta46.service;

import java.util.List;
import com.apap.ta46.model.PaviliunModel;

public interface PaviliunService {
	List<PaviliunModel> getAllPaviliun();
	PaviliunModel getPaviliun(Long id);
}