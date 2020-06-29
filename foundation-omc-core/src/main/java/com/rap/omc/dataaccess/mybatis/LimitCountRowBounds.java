package com.rap.omc.dataaccess.mybatis;

import org.apache.ibatis.session.RowBounds;

import com.rap.omc.dataaccess.exception.DaoException;
public class LimitCountRowBounds extends RowBounds
{
    private final boolean dataCut;
    public LimitCountRowBounds(int limitCount, boolean dataCut)
    {
        super(0, limitCount);
        if (limitCount < 1) throw new DaoException("LimitCount must be greater than 0.");
        this.dataCut = dataCut;
    }

    public boolean isDataCut()
    {
        return this.dataCut;
    }
}