package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.OliveGrove;

import java.util.List;

public interface OliveGroveService {
    public abstract OliveGrove createOliveGrove(Long farmerId,OliveGrove oliveGrove);
    public abstract OliveGrove updateOliveGrove(Long groveId,Long farmerId,OliveGrove oliveGrove);
    public abstract void deleteOliveGrove(Long groveId);
    public abstract List<OliveGrove> getOliveGroves();
    public abstract OliveGrove getOliveGroveById(Long groveId);

}
