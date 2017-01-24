package org.mcclone.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.mcclone.domain.jpa.repositories.ResourceRepository;
import org.mcclone.service.ResourceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.Set;

/**
 * Created by mcclone on 17-1-14.
 */
@Service
@Slf4j
@Transactional
public class ResourceServiceImpl implements ResourceService {

    //直接注入 RequestMappingHandlerMapping
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Resource
    private ResourceRepository resourceRepository;

    @Override
    public void saveMvcUrl() {
        resourceRepository.deleteAllByCreateType(org.mcclone.domain.jpa.entity.Resource.MVC_URL_INIT);
        log.info("删除资源，并开始生成");
        //获取所有的请求信息
        Map<RequestMappingInfo, HandlerMethod> requestMappingInfoMap = requestMappingHandlerMapping.getHandlerMethods();
        Set<RequestMappingInfo> requestMappingInfoSet = requestMappingInfoMap.keySet();
        for (RequestMappingInfo requestMappingInfo : requestMappingInfoSet) {
            //请求路径
            PatternsRequestCondition patternsRequestCondition = requestMappingInfo.getPatternsCondition();
            if (patternsRequestCondition != null) {
                org.mcclone.domain.jpa.entity.Resource resource = new org.mcclone.domain.jpa.entity.Resource();
                resource.setPatterns(patternsResourceResolver.resolver(patternsRequestCondition));

                //请求方法（GET,POST等）
                RequestMethodsRequestCondition requestMethodsRequestCondition = requestMappingInfo.getMethodsCondition();
                resource.setMethods(requestMethodResourceResolver.resolver(requestMethodsRequestCondition));

                //请求的媒体类型（application/json,text/html等）
                ProducesRequestCondition producesCondition = requestMappingInfo.getProducesCondition();
                resource.setMediaType(producesResourceResolver.resolver(producesCondition));

                resource.setCreateType(org.mcclone.domain.jpa.entity.Resource.MVC_URL_INIT);
                this.resourceRepository.save(resource);
            }
        }

    }

    @Override
    public Page<org.mcclone.domain.jpa.entity.Resource> findAll(Pageable pageable) {
        return resourceRepository.findAll(pageable);
    }

    public interface ResourceResolver<T, F> {

        F resolver(T t);
    }

    private static final ResourceResolver<RequestMethodsRequestCondition, String> requestMethodResourceResolver = requestMethodsRequestCondition -> {
        if (requestMethodsRequestCondition != null) {
            Set<RequestMethod> requestMethods = requestMethodsRequestCondition.getMethods();
            if (CollectionUtils.isNotEmpty(requestMethods)) {
                RequestMethod requestMethod = requestMethods.iterator().next();
                return requestMethod.name();
            }
        }
        return null;
    };

    private static final ResourceResolver<PatternsRequestCondition, String> patternsResourceResolver = patternsRequestCondition -> {
        if (patternsRequestCondition != null) {
            Set<String> patterns = patternsRequestCondition.getPatterns();
            if (CollectionUtils.isNotEmpty(patterns)) {
                return patterns.iterator().next();
            }
        }
        return null;
    };

    private static final ResourceResolver<ProducesRequestCondition, String> producesResourceResolver = producesRequestCondition -> {
        if (producesRequestCondition != null) {
            Set<MediaType> mediaTypes = producesRequestCondition.getProducibleMediaTypes();
            if (CollectionUtils.isNotEmpty(mediaTypes)) {
                return mediaTypes.iterator().next().toString();
            }
        }
        return null;
    };

}
