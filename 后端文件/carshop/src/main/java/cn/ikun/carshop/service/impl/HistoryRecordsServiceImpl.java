package cn.ikun.carshop.service.impl;

import cn.ikun.carshop.mapper.HistoryRecordsMapper;
import cn.ikun.carshop.pojo.HistoryRecords;
import cn.ikun.carshop.service.HistoryRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryRecordsServiceImpl extends ServiceImpl<HistoryRecordsMapper, HistoryRecords> implements HistoryRecordsService {
    @Autowired
    private HistoryRecordsMapper historyRecordsMapper;

}
