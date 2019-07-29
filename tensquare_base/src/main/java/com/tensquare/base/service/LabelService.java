package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String labelId){
        return labelDao.findById(labelId).get();//jdk1.8新特性，CrudRepository是一个容器对象 Optional<T> findById(ID var1);通过get去获取里面的值
    }

    public void save(Label label){
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String label){
        labelDao.deleteById(label);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 跟对象，也就是要吧条件封装到哪个对象中。where类名 = label.getid
             * @param criteriaQuery 封装的都是查询关键字。比如：group、by、order by等
             * @param criteriaBuilder 用来封装条件对象的，如果直接返回null，表示不需要任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                //new一个list集合，存放所有的条件
                List<Predicate> list = new ArrayList<>();
                if(label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");//where label like '%JAVA%'
                    list.add(predicate);
                }
                if(label.getState() != null && !"".equals(label.getState())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());//state = '1'
                    list.add(predicate);
                }

                //new一个数组作为最终返回值的条件
                Predicate[] predicates = new Predicate[list.size()];
                //把list直接转成数组
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);//where label like '%JAVA%' and state = '1'
            }
        });
    }

    public Page<Label> pageQuery(Label label, Integer page, Integer size) {
        //分装分页对象
        Pageable pageable = PageRequest.of(page - 1 , size);
        return labelDao.findAll(new Specification<Label>() {
            /**
             *
             * @param root 跟对象，也就是要吧条件封装到哪个对象中。where类名 = label.getid
             * @param criteriaQuery 封装的都是查询关键字。比如：group、by、order by等
             * @param criteriaBuilder 用来封装条件对象的，如果直接返回null，表示不需要任何条件
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                //new一个list集合，存放所有的条件
                List<Predicate> list = new ArrayList<>();
                if(label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");//where label like '%JAVA%'
                    list.add(predicate);
                }
                if(label.getState() != null && !"".equals(label.getState())) {
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());//state = '1'
                    list.add(predicate);
                }

                //new一个数组作为最终返回值的条件
                Predicate[] predicates = new Predicate[list.size()];
                //把list直接转成数组
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);//where label like '%JAVA%' and state = '1'
            }
        }, pageable);
    }
}
