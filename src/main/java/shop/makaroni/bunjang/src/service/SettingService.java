package shop.makaroni.bunjang.src.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.makaroni.bunjang.src.dao.SettingDao;
import shop.makaroni.bunjang.src.domain.setting.model.Notification;
import shop.makaroni.bunjang.src.provider.SettingProvider;


@Service
@Transactional
public class SettingService {
    private final SettingDao settingDao;
    private final SettingProvider settingProvider;

    public SettingService(SettingDao settingDao, SettingProvider settingProvider) {
        this.settingDao = settingDao;
        this.settingProvider = settingProvider;
    }

    public void patchNotification(Long userIdx, Notification req) {
        Notification res = settingDao.getNotification(userIdx);
        if(req.getNA00() != null){res.setNA00(req.getNA00());}
        if(req.getNA01() != null){res.setNA01(req.getNA01());}
        if(req.getNA0100() != null){res.setNA0100(req.getNA0100());}
        if(req.getNA0101() != null){res.setNA0101(req.getNA0101());}
        if(req.getNB00() != null){res.setNB00(req.getNB00());}
        if(req.getNC00() != null){res.setNC00(req.getNC00());}
        if(req.getNC01() != null){res.setNC01(req.getNC01());}
        if(req.getNC02() != null){res.setNC02(req.getNC02());}
        if(req.getNC03() != null){res.setNC03(req.getNC03());}
        if(req.getNC04() != null){res.setNC04(req.getNC04());}
        if(req.getNC05() != null){res.setNC05(req.getNC05());}
        if(req.getND00() != null){res.setND00(req.getND00());}
        if(req.getND01() != null){res.setND01(req.getND01());}
        if(req.getND02() != null){res.setND02(req.getND02());}
        if(req.getNE00() != null){res.setNE00(req.getNE00());}
        if(req.getNE01() != null){res.setNE01(req.getNE01());}
        if(req.getNF00() != null){res.setNF00(req.getNF00());}
        if(req.getNG00() != null){res.setNG00(req.getNG00());}
        settingDao.patchNotification(userIdx, res);
    }
}