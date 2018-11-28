/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.abstractdocument;

import com.iluwatar.abstractdocument.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * The Abstract Document pattern enables handling additional, non-static
 * properties. This pattern uses concept of traits to enable type safety and
 * separate properties of different classes into set of interfaces.
 * <p>
 * <p>
 * In Abstract Document pattern,({@link AbstractDocument}) fully implements
 * {@link Document}) interface. Traits are then defined to enable access to
 * properties in usual, static way.
 *
 * 抽象文档模式：
 * 0、Document接口作为所有对象最基础的抽象，定义 get put children(子文档列表）
 * 1、对象的所有属性 由key value的集合定义，具体属性 由继承 Document 的表示属性的 接口来表示
 * 2、 AbstractDocument 实现 Document 的方法，对象只需要通过实现相关属性的接口来 表达属性
 *
 *  Document 抽象出 属性 get put 和 子文档列表，属性接口 继承 Document 并且定义 属性 key 和 默认获取 属性的方法
 *  AbstractDocument 实现 get put 和 子文档列表 具体操作
 *
 *  实现类 AbstractDocument ， implements 具体的 属性接口 来 表达属性
 *
 *
 *
 *
>>>>>>> e2b5557a8a52fb0353ce9677a7bc8243bb1df10c
 */
public class App {

	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	/**
	 * Executes the App
	 */
	public App() {
		LOGGER.info("Constructing parts and car");

		Map<String, Object> carProperties = new HashMap<>();
		carProperties.put(HasModel.PROPERTY, "300SL");
		carProperties.put(HasPrice.PROPERTY, 10000L);

		Map<String, Object> wheelProperties = new HashMap<>();
		wheelProperties.put(HasType.PROPERTY, "wheel");
		wheelProperties.put(HasModel.PROPERTY, "15C");
		wheelProperties.put(HasPrice.PROPERTY, 100L);

		Map<String, Object> doorProperties = new HashMap<>();
		doorProperties.put(HasType.PROPERTY, "door");
		doorProperties.put(HasModel.PROPERTY, "Lambo");
		doorProperties.put(HasPrice.PROPERTY, 300L);

		carProperties.put(HasParts.PROPERTY, Arrays.asList(wheelProperties, doorProperties));

		Car car = new Car(carProperties);

		LOGGER.info("Here is our car:");
		LOGGER.info("-> model: {}", car.getModel().get());
		LOGGER.info("-> price: {}", car.getPrice().get());
		LOGGER.info("-> parts: ");
		car.getParts().forEach(p -> LOGGER.info("\t{}/{}/{}", p.getType().get(), p.getModel().get(), p.getPrice().get()));
	}

	/**
	 * Program entry point
	 *
	 * @param args command line args
	 */
	public static void main(String[] args) {
		new App();
	}

}
