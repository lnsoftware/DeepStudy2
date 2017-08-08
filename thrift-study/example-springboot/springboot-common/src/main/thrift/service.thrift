include "model.thrift"

namespace java com.zhiyin.thrift.bootexample

service CustomerService{
	model.Customer queryById(1:i64 id);
	
	void create(1:model.Customer customer);
}