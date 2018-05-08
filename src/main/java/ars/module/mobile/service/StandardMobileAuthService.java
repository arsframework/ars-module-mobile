package ars.module.mobile.service;

import java.util.Date;

import ars.invoke.request.Token;
import ars.invoke.request.Requester;
import ars.invoke.event.InvokeBeforeEvent;
import ars.invoke.request.AccessDeniedException;
import ars.module.mobile.app.Device;
import ars.module.mobile.model.Apper;
import ars.module.people.service.StandardAuthService;
import ars.database.repository.Repository;
import ars.database.service.event.UpdateEvent;
import ars.database.repository.Repositories;

/**
 * App用户认证标准实现
 *
 * @author wuyongqiang
 */
public class StandardMobileAuthService extends StandardAuthService implements MobileAuthService {

    @Override
    public void onInvokeEvent(InvokeBeforeEvent event) {

    }

    @Override
    public void onServiceEvent(UpdateEvent event) {

    }

    @Override
    public Token login(Requester requester, String code, String password, Device device) {
        Repository<Apper> repository = Repositories.getRepository(Apper.class);
        Apper apper = repository.query().eq("user", code).single();
        if (apper == null) {
            throw new AccessDeniedException("error.user.unknown");
        }
        Token token = super.login(requester, code, password);
        apper.setDevice(device);
        apper.setChannel(requester.getClient());
        apper.setOnline(true);
        apper.setDateUpdate(new Date());
        repository.update(apper);
        return token;
    }

    @Override
    public void logout(Requester requester) {
        super.logout(requester);
        Repository<Apper> repository = Repositories.getRepository(Apper.class);
        Apper apper = repository.query().eq("user", requester.getUser()).single();
        if (apper != null) {
            apper.setOnline(false);
            apper.setDateUpdate(new Date());
            repository.update(apper);
        }
    }

}
