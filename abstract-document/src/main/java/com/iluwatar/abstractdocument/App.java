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
 * <p>
 * 抽象文档模式，支持 处理附加的、非静态的属性。将对象结构组织为文档树的模式。
 * 实体类属性 来源于 key value，而非定义的属性
 * <p>
 * Document接口,定义 key value的 put get 和获取子文档的方法，是 抽象文档模式下 所有 实体类、抽象类、特征接口类的父类
 * AbstractDocument 实现 Document，保存所有特征的key value集合，所有实体类需要继承 AbstractDocument，和所必要的特征，来实现面向对象
 * 特征接口类 继承Document接口，一个特征接口类表示一个属性，特征接口中定义 获取 特征的默认方法，和 特征的key
 * 实体类实现 特征接口类 表示具有这种属性
 * <p>
 * <p>
 * 所有对象继承于 AbstractDocument, 通过
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
