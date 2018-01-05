/**
 * 
 */
package com.ray.base.base;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.ray.base.util.RayJsonSpread;
/** 
 * 2013-1-9
 * @author wxl 
 */
public class ArrayJsonView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> map,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		Collection<?> collect = map.values();
		//只要一个LIST
		if( collect.size() != 1 ) return ;
		List<?> list = (List<?>) collect.toArray()[0];
		PrintWriter out = response.getWriter();
		out.append( RayJsonSpread.toJSONString( list.toArray()) );
	}

}
