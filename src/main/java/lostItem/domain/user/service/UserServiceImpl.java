package lostItem.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lostItem.Exception.collections.business.DuplicateUniqueKeyException;
import lostItem.Exception.collections.business.PasswordNotMatchException;
import lostItem.Exception.collections.business.UserAlreadyExistsException;
import lostItem.Exception.collections.business.UsernameNotFoundException;
import lostItem.businessProcess.auth.dto.SigninDto;
import lostItem.businessProcess.auth.dto.SignupDto;
import lostItem.domain.user.model.User;
import lostItem.domain.user.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.regex.Pattern.matches;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;


    @Override
    public void checkExits(SignupDto user) {
        //해당 멤버가 존재하는지 확인
        Optional<User> found = this.userRepository.findByEmailId(user.getEmail());
        if(found.isPresent()) throw new UserAlreadyExistsException();

    }

    @Override
    public void register(SignupDto user) {
        //String encodePassword = passwordEncoder.encode(user.getPassword());
        User userEntity = new User( user.getName(), user.getEmail(), user.getPassword());

        try {
            userRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) { //unique 키 중복시 발생시킬 오류
            throw new DuplicateUniqueKeyException();
        }

    }

    @Override
    public void login(SigninDto signInDto) { //UserDetails 객체를 활용한 인가 진행
        Optional<User> found = userRepository.findByEmailId(signInDto.getEmail());

        //이메일 존재하지 않는 경우
        if(!found.isPresent()) throw new UsernameNotFoundException();
        //비밀번호 다른 경우
        if(!matches(signInDto.getPassword(), found.get().getPassword())){
            throw new PasswordNotMatchException();
        }

        //토큰 발급

    }

}
