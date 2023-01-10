package com.sh.ajax.celeb.manager;

import java.util.ArrayList;
import java.util.List;

import com.sh.ajax.celeb.dto.Celeb;
import com.sh.ajax.celeb.dto.CelebType;

/**
 *싱글턴 패턴 패키지 ? 
 * - singletone pattern 
 * - 프로그램 운영중에 딱 하나의 객체만 가지고 사용하는 것 
 * 
 * 1. 생성자를 private으로 만든다 
 *  - 그럼 어떻게 이거 접근하나 ? 
 * 2. 객체 getter 역할을 하는 static메서드를 따로 만든다 (그래야 쓸수있음 ) 
 * 3. 현재객체를 담은 static field를 재사용하는 방식을 사용하게 되는데,,,  
 * 
 *
 */
public class CelebManager {
	private static CelebManager INSTANCE;
	private List<Celeb> celebList = new ArrayList<>();
	
	private CelebManager() {//현재 클래스 외부 에서는 생성자 호출 불가 
		celebList.add( new Celeb(1,"양세찬", "양세찬.jpg", CelebType.COMEDIAN));
		celebList.add( new Celeb(2,"김고은", "김고은.jpg",CelebType.ACTOR ));
		celebList.add( new Celeb(3,"아이유", "아이유.jpg",CelebType.SINGER ));
		celebList.add( new Celeb(4,"조정석", "조정석.jpg",CelebType.ACTOR ));
		celebList.add( new Celeb(5,"강동원", "강동원.jpg",CelebType.ACTOR ));
	}
	
	//하나의 객체만 만들어지게 
	public static CelebManager getInstance() {
		if(INSTANCE==null) {
			INSTANCE = new CelebManager();
		}
		return INSTANCE;
	}//

	
	public List<Celeb> getCelebList() {
		return this.celebList;
	}
	
}
