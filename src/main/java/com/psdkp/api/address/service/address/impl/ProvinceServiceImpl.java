package com.psdkp.api.address.service.address.impl;

import com.psdkp.api.address.domain.Province;
import com.psdkp.api.address.repository.ProvinceDao;
import com.psdkp.api.address.service.address.ProvinceService;
import com.psdkp.api.address.util.ResponMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private ResponMessage responMessage;

    @Override
    public Object findAll(String name, Pageable pageable) {
        return responMessage.SUCCESS_GET(provinceDao.findAllByName(name, pageable));
    }

    @Override
    public Object save(Province province) {
        if (province.getName().equals("") || province.getCode().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Province p = provinceDao.findByCode(province.getCode());
            if (p != null) {
                return responMessage.DUPLICATE("KODE");
            } else {
                Province p2 = provinceDao.findByName(province.getName());
                if (p2 != null) {
                    return responMessage.DUPLICATE("NAMA");
                } else {
                    provinceDao.save(province);
                    return responMessage.SUCCESS_PROCESS_DATA();
                }
            }
        }
    }

    @Override
    public Object edit(Province province) {
        if (province.getId() == null || province.getName().equals("") || province.getCode().equals("")) {
            return responMessage.BAD_REUQEST();
        } else {
            Province pCode = provinceDao.findId(province.getId());
            if (pCode != null) {
                if (province.getCode().equals(pCode.getCode())) {
                    if (pCode.getName().equals(province.getName())){
                        provinceDao.save(province);
                        return responMessage.SUCCESS_PROCESS_DATA();
                    } else {
                        Province p2 = provinceDao.findByName(province.getName());
                        if (p2 != null) {
                            return responMessage.DUPLICATE("NAMA");
                        } else {
                            provinceDao.save(province);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        }
                    }
                } else {
                    Province proCode = provinceDao.findByCode(province.getCode());
                    if (proCode != null) {
                        return responMessage.DUPLICATE("CODE");
                    } else {
                        if (!province.getName().equals(pCode.getName())) {
                            Province p2 = provinceDao.findByName(province.getName());
                            if (p2 != null) {
                                return responMessage.DUPLICATE("NAMA");
                            } else {
                                provinceDao.save(province);
                                return responMessage.SUCCESS_PROCESS_DATA();
                            }
                        } else {
                            provinceDao.save(province);
                            return responMessage.SUCCESS_PROCESS_DATA();
                        }
                    }
                }
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object del(Integer id) {
        if (id == null) {
            return responMessage.BAD_REUQEST();
        } else {
            Province pCode = provinceDao.findId(id);
            if (pCode != null) {
                provinceDao.deleteById(id);
                return responMessage.SUCCESS_PROCESS_DATA();
            } else {
                return responMessage.NOT_FOUND("ID");
            }
        }
    }

    @Override
    public Object findById(Integer id) {
        Province pCode = provinceDao.findId(id);
        if (pCode != null) {
            return responMessage.SUCCESS_GET(provinceDao.findById(id));
        } else {
            return responMessage.NOT_FOUND("ID");
        }
    }
}
