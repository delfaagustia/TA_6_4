package com.apap.ta46.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RawatInapController {
	
//	@GetMapping(value="/get-all-kamar")
//	public List<FlightModel> flightAll() {
//		return flightService.getAllFlight();
//	}
//    @GetMapping(value = "/getPasien")
//    public BaseResponse<List<PasienModel>> getListPasien(@RequestParam(required = true, name = "listId") List<Long> listId) {
//        BaseResponse<List<PasienModel>> response = new BaseResponse<List<PasienModel>>();
//        List<PasienModel> data = pasienDb.findByIdIn(listId);
//        if (data.size() != 0) {
//            response.setStatus(200);
//            response.setMessage("success");
//            response.setResult(pasienDb.findByIdIn(listId));
//        } else {
//            response.setStatus(404);
//            response.setMessage("not found");
//        }
//        return response;
//    }
//    @GetMapping(value = "/{groupId}/getAllPasien")
//    public BaseResponse<List<PasienModel>> getAllPasien(@PathVariable(name = "groupId", required = true) int groupId) {
//        BaseResponse<List<PasienModel>> response = new BaseResponse<List<PasienModel>>();
//        if (groupId > 0 && groupId <= 6) {
//            response.setStatus(200);
//            response.setMessage("success");
//            response.setResult(pasienDb.findByFlagGroup(groupId));
//        } else {
//            response.setStatus(404);
//            response.setMessage("not found");
//        }
//        return response;
//    }
//
//    @GetMapping(value = "/{groupId}/getAllPasienIGD")
//    public BaseResponse<List<PasienModel>> getAllPasienIGD(
//            @PathVariable(name = "groupId", required = true) int groupId) {
//        BaseResponse<List<PasienModel>> response = new BaseResponse<List<PasienModel>>();
//        if (groupId > 0 && groupId <= 6) {
//            response.setStatus(200);
//            response.setMessage("success");
//            response.setResult(pasienDb.findByFlagGroupAndStatusPasienJenisIsContaining(groupId, "IGD"));
//        } else {
//            response.setStatus(404);
//            response.setMessage("not found");
//        }
//        return response;
//    }
//
//    @GetMapping(value = "/{groupId}/getAllPasienRawatJalan")
//    public BaseResponse<List<PasienModel>> getAllPasienRawatJalan(
//            @PathVariable(name = "groupId", required = true) int groupId) {
//        BaseResponse<List<PasienModel>> response = new BaseResponse<List<PasienModel>>();
//        if (groupId > 0 && groupId <= 6) {
//            response.setStatus(200);
//            response.setMessage("success");
//            response.setResult(pasienDb.findByFlagGroupAndStatusPasienJenisIsContaining(groupId, "Rawat Jalan"));
//        } else {
//            response.setStatus(404);
//            response.setMessage("not found");
//        }
//        return response;
//    }
//
//    @GetMapping(value = "/getPasien/{id}")
//    public BaseResponse<PasienModel> getPasienById(@PathVariable(name = "id", required = true) long id) {
//        Optional<PasienModel> data = pasienDb.findById(id);
//        BaseResponse<PasienModel> response = new BaseResponse<>();
//        if (data.isPresent()) {
//            response.setStatus(200);
//            response.setMessage("success");
//            response.setResult(data.get());
//        } else {
//            response.setStatus(404);
//            response.setMessage("not found");
//        }
//        return response;
//    }
	
    
}
