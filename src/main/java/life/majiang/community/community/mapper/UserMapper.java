package life.majiang.community.community.mapper;

import life.majiang.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;



/**
 * @author 12418
 */
@Mapper
public interface UserMapper {
   @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreat},#{gmtModified})")
   void insert(User user);
}
