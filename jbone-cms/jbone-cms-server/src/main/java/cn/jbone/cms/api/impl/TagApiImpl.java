package cn.jbone.cms.api.impl;

import cn.jbone.cms.api.TagApi;
import cn.jbone.cms.common.dataobject.PagedResponseDO;
import cn.jbone.cms.common.dataobject.TagCommonRequestDO;
import cn.jbone.cms.common.dataobject.TagDO;
import cn.jbone.cms.core.service.TagService;
import cn.jbone.common.rpc.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagApiImpl implements TagApi {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TagService tagService;

    @Override
    public Result<List<TagDO>> getAll() {
        List<TagDO> tagDOS;
        try {
            tagDOS = tagService.findAll();
        } catch (Exception e) {
            logger.warn("Tag getAll error.",e);
            return Result.wrap500Error(e.getMessage());
        }
        return Result.wrapSuccess(tagDOS);
    }

    @Override
    public Result<List<TagDO>> getByName(String name) {
        List<TagDO> tagDOS;
        try {
            tagDOS = tagService.getByName(name);
        } catch (Exception e) {
            logger.warn("Tag getByName error.",e);
            return Result.wrap500Error(e.getMessage());
        }
        return Result.wrapSuccess(tagDOS);
    }

    @Override
    public Result<TagDO> getById(Long id) {
        TagDO tagDO = null;
        try {
            tagDO = tagService.getById(id);
        } catch (Exception e) {
            logger.warn("Tag getById error.",e);
            return Result.wrap500Error(e.getMessage());
        }
        return Result.wrapSuccess(tagDO);
    }

    @Override
    public Result<Void> delete(Long id) {
        try {
            tagService.delete(id);
        } catch (Exception e) {
            logger.warn("Tag delete error.",e);
            return Result.wrap500Error(e.getMessage());
        }
        return Result.wrapSuccess();
    }

    @Override
    public Result<Void> addOrUpdate(@RequestBody TagDO tagDO) {
        try {
            tagService.addOrUpdate(tagDO);
        } catch (Exception e) {
            logger.warn("Tag addOrUpdate error.",e);
            return Result.wrap500Error(e.getMessage());
        }
        return Result.wrapSuccess();
    }

    @Override
    public Result<PagedResponseDO<TagDO>> commonRequest(@RequestBody TagCommonRequestDO tagCommonRequestDO) {
        PagedResponseDO<TagDO> pagedResponseDO = null;
        try {
            pagedResponseDO = tagService.commonRequest(tagCommonRequestDO);
        } catch (Exception e) {
            logger.warn("Tag commonRequest error.",e);
            return Result.wrap500Error(e.getMessage());
        }
        return Result.wrapSuccess(pagedResponseDO);
    }
}
