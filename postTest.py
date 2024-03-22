#Python으로 데이터가 es에 제대로 박히는지 확인하는 과정
from elasticsearch import Elasticsearch
import requests
from requests.packages.urllib3.exceptions import InsecureRequestWarning
import random
import uuid

# SSL 경고 비활성화
requests.packages.urllib3.disable_warnings(InsecureRequestWarning)

# Elasticsearch 인스턴스 생성 - SSL 인증서 검증 비활성화
es = Elasticsearch(
    ["https://43.203.143.217:9200"],
    http_auth=('elastic', 'elastic'),  # 필요한 경우 사용자 이름과 비밀번호
    verify_certs=False,  # SSL 인증서 검증 비활성화
    # 다른 SSL 관련 설정 필요 시 여기에 추가
)

# 랜덤 데이터 생성 및 색인에 저장하는 함수
def create_and_store_random_data(index_name):
    for _ in range(5):
        # 랜덤 데이터 생성
        document = {
            "name": "Name_" + str(random.randint(1, 100)),
            "age": random.randint(20, 60),
            "interests": ["interest_" + str(random.randint(1, 10)) for _ in range(3)],
            "city": "City_" + str(random.randint(1, 10))
        }
        
        # 랜덤 생성된 UUID를 문서 ID로 사용
        doc_id = str(uuid.uuid4())
        
        # 문서 저장
        res = es.index(index=index_name, id=doc_id, document=document)
        print("Document ID: {}. Indexing result: {}".format(doc_id, res['result']))

# 색인 이름 설정 (Elasticsearch에서 사용할 색인)
index_name = "random_data"

# 랜덤 데이터 생성 및 저장
create_and_store_random_data(index_name)
