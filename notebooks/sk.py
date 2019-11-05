#import skil_client
#
#configuration = skil_client.Configuration()
#configuration.host = 'http://52.15.103.124:9008'
#configuration.api_key['authorization'] = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJTa2lsVXNlciIsInN1YiI6IntcInVzZXJJZFwiOlwiYWRtaW5cIixcInVzZXJOYW1lXCI6XCJhZG1pblwiLFwicm9sZVwiOlwiYWRtaW5cIixcInNjb3BlXCI6XCJhZG1pblwifSIsImlzcyI6IlNraWxBdXRoTWFuYWdlciIsImV4cCI6MTU4NjgyODcxMCwiaWF0IjoxNTU1MjkyNzEwfQ.vXXpr0kk9H8nMbkcM8sPWPJwuws1GGvI8SWc76qKvUo'
#api_instance = skil_client.DefaultApi(skil_client.ApiClient(configuration))
#api_response = api_instance.predictimage("yolo", "default", "yolomodel", image='input.jpg')
#
from skil import Skil

skil_server = Skil(host='52.15.103.124', password='admin123')

from skil import WorkSpace, Experiment, Model

work_space = WorkSpace(skil_server, name="Object detection project")
experiment = Experiment(work_space, name="YOLO")
model = Model('yolo_v2.pb', name='yolo_model', experiment=experiment)
#
from skil import Deployment

deployment = Deployment(skil_server)
service = model.deploy(deployment, scale=1, input_names=['input'], output_names=['output'])
#
#from skil.utils.yolo import annotate_image
#import cv2
#
#image = cv2.imread('input.jpg')
#detection = service.detect_objects(image)
#print(detection)
#image = annotate_image(image, detection)
#cv2.imwrite('./annotated.jpg', image)
