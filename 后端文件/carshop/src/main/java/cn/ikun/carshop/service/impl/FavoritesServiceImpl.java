package cn.ikun.carshop.service.impl;

import cn.ikun.carshop.mapper.FavoritesMapper;
import cn.ikun.carshop.pojo.Favorites;
import cn.ikun.carshop.service.FavoritesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements FavoritesService {
    @Autowired
    private FavoritesMapper favoritesMapper;
}
