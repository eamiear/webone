package com.webone.core.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * 自定义拓展接口
 * @author skz
 * @date 2015年9月7日
 * @time 下午9:10:29
 * @param <T>
 * @param <PK>
 */
@NoRepositoryBean
public interface BaseRepository<T, PK extends Serializable> extends JpaRepository<T, ID> {

}
