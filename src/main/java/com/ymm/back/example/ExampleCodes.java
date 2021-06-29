package com.ymm.back.example;

public class ExampleCodes {
//    public int patch(int id, ProjectEntity req) {
//        Optional<ProjectEntity> project = projectRepository.findById(id);
//        if(project.isPresent()) {
//            ProjectEntity pj = project.get();
//            if(StringUtils.isNotBlank(req.getName()))
//                pj.setName(req.getName());
//            //날짜는 toString으로 바꿔서 바뀌어지는지 체크하면 된다.
//            System.out.println("종료일값: --------"+req.getFinishedAt());
//            if(req.getFinishedAt() != null)
//                pj.setFinishedAt(req.getFinishedAt());
//            if(StringUtils.isNotBlank(req.getDescription()))
//                pj.setDescription(req.getDescription());
//            if(StringUtils.isNotBlank(req.getThumbnail()))
//                pj.setThumbnail(req.getThumbnail());
//            if(StringUtils.isNotBlank(req.getContents()))
//                pj.setContents(req.getAuthorization());
//            //아직 Entity와 DB에 안만들어진 상태다.
////            if(StringUtils.isNotBlank(req.getUrl()))
////                pj.setUrl(req.getUrl());
//            projectRepository.save(pj);
//            return 1;
//        }
//        return 0;
//    }
    //        @PostMapping("/ins")
//        public Message createProjects2(Map<String,Object> req){
//            //일단 안넣으면 모두 null 값으로 날아갑니다.
//            ProjectEntity pj = new ProjectEntity();
//            req.entrySet().forEach((key, value)->{
//                if(value !=null){
//                    Object a= req.get(key);
//                }
//                //ProjectEntity pj= projectService.createProject(req);
//            });
//            for( Object o : safe( list ) ) {
//                // do whatever
//            }
//
//            public static List safe( List other ) {
//                return other == null ? Collections.EMPTY_LIST : other;
//            }
//
//            return new Message("success",pj); //null
//        }

}
