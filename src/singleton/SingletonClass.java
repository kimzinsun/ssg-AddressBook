package singleton;

import java.util.ArrayList;
import java.util.List;

import dto.AddressDto;

public class SingletonClass {

	private static SingletonClass sc = null;

	public List<AddressDto> list;

	private SingletonClass() {
		list = new ArrayList<AddressDto>();
	}

	public static SingletonClass getInstance() {
		if (sc == null) {
			sc = new SingletonClass();
		}
		return sc;
	}
}
